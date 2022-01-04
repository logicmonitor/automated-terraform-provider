package schemata 

{{- $operationGroup := pascalize .Name -}}
{{- $isResource := or (eq $operationGroup "Collector") (eq $operationGroup "CollectorGroup") (eq $operationGroup "Device") (eq $operationGroup "DeviceGroup") -}}
{{- $isDataResource := eq .Name "AwsExternalId" -}}
{{- $hasID := false -}}

{{- $isReadOnlyModel := true -}}
{{- $needsUtils := false -}}
{{- range .Properties -}}
	{{- if not .ReadOnly -}} {{- $isReadOnlyModel = false -}} {{- end -}}
	{{- if or (eq .Name "collectors") (eq .Name "customProperties") (eq .Name "tags") (eq .GoType "[]string") -}} {{- $needsUtils = true -}} {{- end -}}
	{{- if eq .Name "id" -}} {{- $hasID = true -}} {{- end -}}
{{- end }}

import (
	{{- if or (eq $isResource true) (and (eq $isDataResource true) (eq $hasID false)) }}
	"strconv"
	{{- end }}
	{{- if and (eq $isDataResource true) (eq $hasID false) }}
	"time"
	{{- end }}
	{{- if or $isResource $needsUtils }}
	"terraform-provider-logicmonitor/logicmonitor/utils"
	{{- end }}
	"terraform-provider-logicmonitor/models"
	"github.com/hashicorp/terraform-plugin-sdk/v2/helper/schema"
)

func {{ $operationGroup }}Schema() map[string]*schema.Schema {
	return map[string]*schema.Schema{
		{{- range .Properties }}
		"{{ snakize .Name }}": {
				{{- if and $isResource (eq .Name "id") }}
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

{{- if or $isResource $isDataResource }}

func Set{{ $operationGroup }}ResourceData(d *schema.ResourceData, m *models.{{ $operationGroup }}) {
	{{- range .Properties }}
		{{- if and $isResource (eq .Name "id") }}
	d.Set("id", strconv.Itoa(int(m.ID)))
		{{- else if or .IsComplexObject }}
	d.Set("{{ snakize .Name }}", Set{{ pascalize .GoType }}SubResourceData([]*models.{{ pascalize .GoType }}{m.{{ pascalize .Name }}}))
		{{- else if stringContains .GoType "[]*" }}
	d.Set("{{ snakize .Name }}", Set{{ pascalize .Items.GoType }}SubResourceData(m.{{ pascalize .Name }}))
		{{- else }}
	d.Set("{{ snakize .Name }}", m.{{ pascalize .Name }})
		{{- end }}
	{{- end }}
	{{- if and (eq $isDataResource true) (eq $hasID false) }}
	d.SetId(strconv.FormatInt(time.Now().Unix(), 10))
	{{- end }}
}
{{- end }}

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

{{ if $isResource -}}
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
	{{ varname .Name }} := d.Get("{{ snakize .Name }}").(*models.{{ pascalize .GoType }})

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
{{- else -}}
func {{ $operationGroup }}Model(d map[string]interface{}) *models.{{ $operationGroup }} {
	// assume that the incoming map only contains the relevant resource data
	{{- range .Properties }}
		{{- if or (not .ReadOnly) (and (eq .Name "id") (not $isReadOnlyModel)) }}
			{{- if .IsComplexObject }}
	var {{ varname .Name }} *models.{{ pascalize .GoType }} = nil
	{{ .Name }}List := d["{{ snakize .Name }}"].([]interface{})
	if len({{ .Name }}List) > 0 { // len(nil) = 0
		{{ varname .Name }} = {{ .GoType }}Model({{ .Name }}List[0].(map[string]interface{}))
	}

			{{- else if stringContains .Name "Properties" }}
	{{ varname .Name }} := utils.GetPropertiesFromMap(d, "{{ snakize .Name }}")

			{{- else if eq .Name "collectors" }}
	{{ varname .Name }} := utils.GetCloudCollectorConfigs(d["{{ .Name }}"].([]interface{}))
			
			{{- else if eq .Name "tags" }}
	{{ varname .Name }} := utils.GetCloudTagFilters(d["{{ .Name }}"].([]interface{}))
			
			{{- else if eq .GoType "interface{}" }}
	{{ varname .Name }} := d["{{ snakize .Name }}"]

			{{- else if or (eq .GoType "int32") ( eq .GoType "int64") }}
	{{ varname .Name }} := {{ .GoType }}(d["{{ snakize .Name }}"].(int))

			{{- else if eq .GoType "[]string" }}
	{{ varname .Name }} := utils.ConvertSetToStringSlice(d["{{ snakize .Name }}"].(*schema.Set))

			{{- else if stringContains .GoType "[]*" }}
				{{- if stringContains $operationGroup "PaginationResponse" }}
	{{ varname .Name }} := d["{{ snakize .Name }}"].([]*models.{{ pascalize (slice .GoType 3) }})
				{{- else }}
	{{ varname .Name }} := d["{{ snakize .Name }}"].([]*models.{{ pascalize .GoType }})
				{{- end }}

			{{- else if and (not (eq .GoType "string")) (not (eq .GoType "[]string")) (not (eq .GoType "bool")) (not (eq .GoType "int")) (not (eq .GoType "float32")) (not (eq .GoType "float64")) (not (eq .GoType "uint32")) (not (eq .GoType "uint64")) }}
	{{ varname .Name }} := d["{{ snakize .Name }}"].(*models.{{ pascalize .GoType }})

			{{- else }}
	{{ varname .Name }} := d["{{ snakize .Name }}"].({{ .GoType }})
			{{- end }}
		{{- end }}
	{{- end }}
	
	return &models.{{ $operationGroup }} {
		{{- if not $isReadOnlyModel }}
		{{- range .Properties }}
			{{- if or (not .ReadOnly) (eq .Name "id") }}
				{{- if and (and (not .IsMap) .IsNullable (not .IsSuperAlias)) (not .IsComplexObject) }}
		{{ pascalize .Name }}: &{{ varname .Name }},
				{{- else }}
		{{ pascalize .Name }}: {{ varname .Name }},
				{{- end }}
			{{- end }}
		{{- end }}
		{{- end }}
	}
}
{{ end }}
func Get{{ $operationGroup }}PropertyFields() (t []string) {
	return []string{
		{{- range .Properties }}
			{{- if or (not .ReadOnly) (eq .Name "id") }}
		"{{ snakize .Name }}",
			{{- end }}
		{{- end }}
	}
}