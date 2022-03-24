package schemata 

{{- $operationGroup := pascalize .Name -}}
{{- $hasID := false -}}

{{/* Some property fields can be characterized as more "complex" objects whose data must be retrieved with specialized helper functions */}}
{{- $isReadOnlyModel := true -}}
{{- $needsUtils := false -}}
{{- range .Properties -}}
	{{- if not .ReadOnly -}} {{- $isReadOnlyModel = false -}} {{- end -}}
	{{- if or (eq .Name "collectors") (eq .Name "customProperties") (eq .Name "tags") (eq .GoType "[]string") -}} {{- $needsUtils = true -}} {{- end -}}
	{{- if eq .Name "id" -}} {{- $hasID = true -}} {{- end -}}
{{- end }}

import (
	{{- if $needsUtils }}
	"tf-provider-example/logicmonitor/utils"
	{{- end }}
)

// Schema mapping representing the {{ $operationGroup }} resource defined in the Terraform configuration
func {{ $operationGroup }}Schema() map[string]*schema.Schema {
	return map[string]*schema.Schema{
		{{- range .Properties }}
		"{{ snakize .Name }}": {
				{{- if (eq .Name "id") }}
			Type: schema.TypeString,
			Computed: true,
				{{- else }}
					{{- if eq .GoType "string" }}
			Type: schema.TypeString,
						{{- if .Default }}
			Default: "{{ .Default }}",
						{{- end }}
					{{- else if eq .GoType "bool" }}
			Type: schema.TypeBool,
					{{- if .Default }}
			Default: {{ .Default }},
						{{- end }}
					{{- else if eq .GoType "float64" }}
			Type: schema.TypeFloat,
					{{- else if eq .GoType "float32" }}
			Type: schema.TypeFloat,
					{{- else if eq .GoType "int64" }}
			Type: schema.TypeInt,
					{{- else if eq .GoType "int32" }}
			Type: schema.TypeInt,
					{{- else if eq .GoType "int" }}
			Type: schema.TypeInt,
					{{- else if eq .GoType "uint64" }}
			Type: schema.TypeInt,
					{{- else if eq .GoType "uint32" }}
			Type: schema.TypeInt,
					{{- else if eq .GoType "[]string" }}
			Type: schema.TypeSet,
			Elem:     &schema.Schema{Type: schema.TypeString},
			Set:      schema.HashString,
					{{- else if eq .GoType "interface{}" }}
			Type: schema.TypeMap, //GoType: {{ .GoType }}
			Elem: &schema.Schema{
				Type: schema.TypeString,
			},
					{{- else }}
			Type: schema.TypeList, //GoType: {{ .GoType }}
						{{- if stringContains .GoType "[]*" }} 
			Elem: &schema.Resource{
				Schema: {{ .Items.GoType }}Schema(),
			},
			ConfigMode: schema.SchemaConfigModeAttr,
						{{- else }}
			Elem: &schema.Resource{
				Schema: {{ .GoType }}Schema(),
			},
						{{- end }}
					{{- end }}
					{{- if and .Required (not .ReadOnly) }}
			Required: true,
					{{- else if .ReadOnly }}
			Computed: true,
					{{- else }}
			Optional: true,
					{{- end }}
				{{- end }}
		},
		{{ end }}
	}
}

{{/* Only resources have respective data sources that need to be mapped to an appropriate schema */}}
{{- if eq .Example "isResource" }}
// Schema mapping representing the resource's respective datasource object defined in Terraform configuration
// Only difference between this and {{ $operationGroup }}Schema() are the computabilty of the id field and the inclusion of a filter field for datasources
func DataSource{{ $operationGroup }}Schema() map[string]*schema.Schema {
	return map[string]*schema.Schema{
		{{- range .Properties }}
		"{{ snakize .Name }}": {
				{{- if (eq .Name "id") }}
			Type: schema.TypeString,
				{{- else }}
					{{- if eq .GoType "string" }}
			Type: schema.TypeString,
						{{- if .Default }}
			Default: "{{ .Default }}",
						{{- end }}
					{{- else if eq .GoType "bool" }}
			Type: schema.TypeBool,
					{{- if .Default }}
			Default: {{ .Default }},
						{{- end }}
					{{- else if eq .GoType "float64" }}
			Type: schema.TypeFloat,
					{{- else if eq .GoType "float32" }}
			Type: schema.TypeFloat,
					{{- else if eq .GoType "int64" }}
			Type: schema.TypeInt,
					{{- else if eq .GoType "int32" }}
			Type: schema.TypeInt,
					{{- else if eq .GoType "int" }}
			Type: schema.TypeInt,
					{{- else if eq .GoType "uint64" }}
			Type: schema.TypeInt,
					{{- else if eq .GoType "uint32" }}
			Type: schema.TypeInt,
					{{- else if eq .GoType "[]string" }}
			Type: schema.TypeSet,
			Elem:     &schema.Schema{Type: schema.TypeString},
			Set:      schema.HashString,
					{{- else if eq .GoType "interface{}" }}
			Type: schema.TypeMap, //GoType: {{ .GoType }}
			Elem: &schema.Schema{
				Type: schema.TypeString,
			},
					{{- else }}
			Type: schema.TypeList, //GoType: {{ .GoType }}
						{{- if stringContains .GoType "[]*" }} 
			Elem: &schema.Resource{
				Schema: {{ .Items.GoType }}Schema(),
			},
			ConfigMode: schema.SchemaConfigModeAttr,
						{{- else }}
			Elem: &schema.Resource{
				Schema: {{ .GoType }}Schema(),
			},
						{{- end }}
					{{- end }}
			Optional: true,
				{{- end }}
		},
		{{ end }}
		"filter": {
			Type:     schema.TypeString,
            Optional: true,
		},
	}
}
{{- end }}

// Update the underlying {{ $operationGroup }} resource data in the Terraform configuration using the resource model built from the CREATE/UPDATE/READ LM API request response
func Set{{ $operationGroup }}ResourceData(d *schema.ResourceData, m *models.{{ $operationGroup }}) {
	{{- range .Properties }}
		{{- if (eq .Name "id") }}
	d.Set("id", strconv.Itoa(int(m.ID)))
		{{- else if or .IsComplexObject }}
	d.Set("{{ snakize .Name }}", Set{{ pascalize .GoType }}SubResourceData([]*models.{{ pascalize .GoType }}{m.{{ pascalize .Name }}}))
		{{- else if stringContains .GoType "[]*" }}
	d.Set("{{ snakize .Name }}", Set{{ pascalize .Items.GoType }}SubResourceData(m.{{ pascalize .Name }}))
		{{- else }}
	d.Set("{{ snakize .Name }}", m.{{ pascalize .Name }})
		{{- end }}
	{{- end }}
}

// Iterate throught and update the {{ $operationGroup }} resource data within a pagination response (typically defined in the items array field) retrieved from a READ operation for multiple LM resources
func Set{{ $operationGroup }}SubResourceData(m []*models.{{ $operationGroup }}) (d []*map[string]interface{}) {
	{{- $model := camelize $operationGroup }}
	for _, {{ $model }} := range m {
		if {{ $model }} != nil {
			properties := make(map[string]interface{})
			{{- range .Properties }}
				{{- if or .IsComplexObject }}
			properties["{{snakize .Name}}"] = Set{{ pascalize .GoType }}SubResourceData([]*models.{{ pascalize .GoType }}{ {{- $model }}.{{ pascalize .Name -}} })
				{{- else if stringContains .GoType "[]*" }}
			properties["{{snakize .Name}}"] = Set{{ pascalize .Items.GoType }}SubResourceData({{ $model }}.{{ pascalize .Name }})
				{{- else }}
			properties["{{ snakize .Name }}"] = {{ $model }}.{{ pascalize .Name }}
				{{- end }}
			{{- end }}
			d = append(d, &properties)
		}
	}
	return
}

// Function to perform the following actions:
// (1) Translate {{ $operationGroup }} resource data into a schema model struct that will sent to the LM API for resource creation/updating
// (2) Translate LM API response object from (1) or from a READ operation into a model that can be used to mofify the underlying resource data in the Terrraform configuration
func {{ $operationGroup }}Model(d *schema.ResourceData) *models.{{ $operationGroup }} {
	{{- range .Properties }}
		{{- if or (not .ReadOnly) (and (eq .Name "id") (not $isReadOnlyModel)) }}
			{{- if (eq .Name "id") }}
	id, _ := strconv.Atoi(d.Get("id").(string))

			{{- else if .IsComplexObject }}
	var {{ varname .Name }} *models.{{ pascalize .GoType }} = nil
	{{ .Name }}Interface, {{ .Name }}IsSet := d.GetOk("{{ snakize .Name }}")
	if {{ .Name }}IsSet {
		{{ .Name }}Map := {{ .Name }}Interface.([]interface{})[0].(map[string]interface{})
		{{ varname .Name }} = {{ .GoType }}Model({{ .Name }}Map)
	}
			
			{{- else if stringContains .Name "Properties" }}
	{{ varname .Name }} := utils.GetPropertiesFromResource(d, "{{ snakize .Name }}")

			{{- else if eq .Name "collectors" }}
	{{ varname .Name }} := utils.GetCloudCollectorConfigs(d)
			
			{{- else if eq .Name "tags" }}
	{{ varname .Name }} := utils.GetCloudTagFilters(d)
			
			{{- else if eq .GoType "interface{}" }}
	{{ varname .Name }} := d.Get("{{ snakize .Name }}")

			{{- else if or (eq .GoType "int32") ( eq .GoType "int64") }}
	{{ varname .Name }} := {{ .GoType }}(d.Get("{{ snakize .Name }}").(int))

			{{- else if eq .GoType "[]string" }}
	{{ varname .Name }} := utils.ConvertSetToStringSlice(d.Get("{{ snakize .Name }}").(*schema.Set))

			{{- else if and (not (eq .GoType "string")) (not (eq .GoType "[]string")) (not (eq .GoType "bool")) (not (eq .GoType "int")) (not (eq .GoType "float32")) (not (eq .GoType "float64")) (not (eq .GoType "uint32")) (not (eq .GoType "uint64")) }}
	{{ varname .Name }} := d.Get("{{ snakize .Name }}").({{ if hasPrefix .GoType "[]" }}[]{{ end }}*models.{{ pascalize .GoType }})

			{{- else }}
	{{ varname .Name }} := d.Get("{{ snakize .Name }}").({{ .GoType }})
			{{- end }}
		{{- end }}
	{{- end }}
	
	return &models.{{ $operationGroup }} {
		{{- if not $isReadOnlyModel }}
		{{- range .Properties }}
			{{- if or (not .ReadOnly) (eq .Name "id") }}
				{{- if eq .Name "id" }}
		{{ pascalize .Name }}: int32({{ varname .Name }}),
				{{- else if and (and (not .IsMap) .IsNullable (not .IsSuperAlias)) (not .IsComplexObject) }}
		{{ pascalize .Name }}: &{{ varname .Name }},
				{{- else }}
		{{ pascalize .Name }}: {{ varname .Name }},
				{{- end }}
			{{- end }}
		{{- end }}
		{{- end }}
	}
}

// Retrieve property field names for updating the {{ $operationGroup }} resource 
func Get{{ $operationGroup }}PropertyFields() (t []string) {
	return []string{
		{{- range .Properties }}
			{{- if or (not .ReadOnly) (eq .Name "id") }}
		"{{ snakize .Name }}",
			{{- end }}
		{{- end }}
	}
}