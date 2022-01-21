//// Code generated by go-swagger; DO NOT EDIT.

{{ if .Copyright -}}// {{ comment .Copyright -}}{{ end }}

{{ define "stringToIntConversion" }}
	{{ camelize .ID }}, _ := strconv.Atoi({{ camelize .ID }}Val.(string))
	params.{{ pascalize .ID}} = {{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}{{ end }}{{ if not .IsFileParam }}{{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}&{{ end }}int32({{ camelize .ID }}){{ else }}runtime.NamedReadCloser{{- end -}}
{{ end }}

{{- $operationGroup := .Name -}}
{{- $isResource := or (eq $operationGroup "collector") (eq $operationGroup "collector_group") (eq $operationGroup "device") (eq $operationGroup "device_group")}}
{{- $isDataResource := hasPrefix $operationGroup "data_resource_" }}

package resources

// This file was generated by the swagger tool.
// Editing this file might prove futile when you re-run the swagger generate command

{{/* If you want to manually create imports be sure to turn on skip_format for this template */}}
import (
	"tf-provider-example/logicmonitor/utils"
)

/*
{{ pascalize $operationGroup }} {{ if .Summary }}{{ pluralizeFirstWord (humanize .Summary) }}{{ if .Description }}

{{ blockcomment .Description }}{{ end }}{{ else if .Description}}{{ blockcomment .Description }}{{ else }}{{ humanize $operationGroup }} API{{ end }}
*/

func {{ pascalize $operationGroup }}() *schema.Resource {
	return &schema.Resource{
		{{- range .Operations }}
			{{- $operation := .Name }}
			{{- if and (not (stringContains $operation "misc")) (not (stringContains $operation "List")) }}
			{{- if eq .Method "GET" }}
		ReadContext: {{ $operation }},
			{{- end }}
			{{- if eq .Method "POST" }}
		CreateContext: {{ $operation }},
			{{- end }}
			{{- if eq .Method "PUT" }}
		UpdateContext: {{ $operation }},
			{{- end }}
			{{- if eq .Method "DELETE" }}
		DeleteContext: {{ $operation }},
			{{- end }}
			{{- end }}
		{{- end }}
		{{- if $isResource }}
		Importer: &schema.ResourceImporter{
			State: resource{{ pascalize $operationGroup }}StateImporter,
		},
		{{- end }}
		Schema: schemata.{{ if $isDataResource -}} {{- pascalize (slice $operationGroup 13) -}} {{- else -}} {{- pascalize $operationGroup -}} {{- end }}Schema(),
	}
}

{{ range .Operations }}
	{{- $operation := .Name }}
	{{- if and (not (stringContains $operation "misc")) (not (stringContains $operation "List")) }}
	{{- if (eq .Method "GET") }}
func {{ $operation }}(ctx context.Context, d *schema.ResourceData, m interface{}) diag.Diagnostics {
	var diags diag.Diagnostics
	
	params := {{ $operationGroup }}.New{{ pascalize $operation }}Params()

	{{ range .Params }}
		{{ camelize .ID }}Val, {{ camelize .ID }}IsSet := d.GetOk("{{ snakize .ID}}")
		if({{ camelize .ID }}IsSet){
			{{- if .IsPrimitive -}}
				{{- if eq (camelize .ID) "id" }}
					{{- template "stringToIntConversion" . -}}
				{{- else }}
					{{- if eq .GoType "int32" }}
						i := int32({{ camelize .ID }}Val.(int))
						params.{{ pascalize .ID}} = {{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}{{ end }}{{ if not .IsFileParam }}{{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}&{{ end }}i{{ else }}runtime.NamedReadCloser{{- end -}}
					{{- else }}
						params.{{ pascalize .ID}} = {{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}{{ end }}{{ camelize .ID }}Val.({{ if not .IsFileParam }}{{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}*{{ end }}{{ .GoType }}{{ else }}runtime.NamedReadCloser{{ end }})
					{{- end }}
				{{- end }}
			{{- else }}
				params.{{ pascalize .ID}} = {{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}{{ end }}{{ camelize .ID }}Val.({{ if not .IsFileParam }}{{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}*{{ end }}{{ .GoType }}{{ else }}runtime.NamedReadCloser{{ end }})
			{{- end }}
		} {{ if .Required }} else {
			diags = append(diags, diag.Errorf("unexpected: Missing parameter - {{ .Name }}")...)
			diags = append(diags, diag.Errorf("ending operation")...)
			return diags
		} {{ end }}
	{{ end }}

	{{ if .HasStreamingResponse }}
		var Discard io.Writer = io.Discard
	{{ end }}

	client := m.(*client.LogicMonitorRESTAPI)

	resp, err := client.{{ pascalize $operationGroup }}.{{ pascalize $operation }}(params {{ if .HasStreamingResponse }}, Discard {{ end }})
	log.Printf("[TRACE] response: %v", resp)
	if(err != nil){
		diags = append(diags, diag.Errorf("unexpected: %s", err)...)
	}
	
	respModel := resp.GetPayload()
	schemata.Set{{ if $isDataResource -}} {{- pascalize (slice $operationGroup 13) -}} {{- else -}} {{- pascalize $operationGroup -}} {{- end }}ResourceData(d, respModel)

	return diags
}
	{{- end }}

	{{- if eq .Method "POST" }}
func {{ $operation }}(ctx context.Context, d *schema.ResourceData, m interface{}) diag.Diagnostics {
	var diags diag.Diagnostics

	model := schemata.{{ pascalize $operationGroup }}Model(d)
	params := {{ $operationGroup }}.New{{ pascalize $operation }}Params()
	params.SetBody(model)

	{{ if .HasStreamingResponse }}
		var Discard io.Writer = io.Discard
	{{ end}}

	client := m.(*client.LogicMonitorRESTAPI)

	resp, err := client.{{ pascalize $operationGroup }}.{{ pascalize $operation }}(params {{ if .HasStreamingResponse }}, Discard {{ end }})
	log.Printf("[TRACE] response: %v", resp)
	if(err != nil){
		diags = append(diags, diag.Errorf("unexpected: %s", err)...)
		return diags
	}

	respModel := resp.GetPayload()
	schemata.Set{{ pascalize $operationGroup }}ResourceData(d, respModel)
	d.SetId(strconv.Itoa(int(resp.Payload.ID)))

	return diags
}
	{{- end }}

	{{- if eq .Method "PUT" }}
func {{ $operation }}(ctx context.Context, d *schema.ResourceData, m interface{}) diag.Diagnostics {
	var diags diag.Diagnostics
	d.Partial(true)

	model := schemata.{{pascalize $operationGroup }}Model(d)
	params := {{ $operationGroup }}.New{{ pascalize $operation }}Params()

	{{ range .Params }}
		{{ if eq (camelize .ID) "body" }}
			params.SetBody(model)
		{{ else }}
			{{ camelize .ID }}Val, {{ camelize .ID }}IsSet := d.GetOk("{{ snakize .ID}}")
			if({{ camelize .ID }}IsSet){
				{{- if .IsPrimitive -}}
					{{- if eq (camelize .ID) "id" }}
						{{- template "stringToIntConversion" . -}}
					{{- else }}
						{{- if eq .GoType "int32" }} 
							i := int32({{ camelize .ID }}Val.(int))
							params.{{ pascalize .ID}} = {{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}{{ end }}{{ if not .IsFileParam }}{{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}&{{ end }}i{{ else }}runtime.NamedReadCloser{{- end -}}
						{{- else }}
							params.{{ pascalize .ID}} = {{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}{{ end }}{{ camelize .ID }}Val.({{ if not .IsFileParam }}{{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}*{{ end }}{{ .GoType }}{{ else }}runtime.NamedReadCloser{{ end }})
						{{- end }}
					{{- end }}
				{{- else }}
					params.{{ pascalize .ID}} = {{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}{{ end }}{{ camelize .ID }}Val.({{ if not .IsFileParam }}{{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}*{{ end }}{{ .GoType }}{{ else }}runtime.NamedReadCloser{{ end }})
				{{- end }}
			} {{ if .Required }} else {
				diags = append(diags, diag.Errorf("unexpected: Missing parameter - {{ .Name }}")...)
				diags = append(diags, diag.Errorf("ending operation")...)
				return diags
			} {{ end }}
		{{ end }}
	{{ end }}

	// list of available properties
	props := schemata.Get{{ pascalize $operationGroup }}PropertyFields()

	// loops through array of properties to see which one has changed, the ones that did not change are removed from the list
	for _, v := range props {
		if d.HasChange(v) {
		} else {
			props = utils.Remove(props, v)
		}
	}

	client := m.(*client.LogicMonitorRESTAPI)

	// makes a bulk update for all properties that were changed
	resp, err := client.{{ pascalize $operationGroup }}.{{ pascalize $operation }}(params {{ if .HasStreamingResponse }}, Discard {{ end }})
	log.Printf("[TRACE] response: %v", resp)
	if(err != nil){
		diags = append(diags, diag.Errorf("unexpected: %s", err)...)
		return diags
	}

	respModel := resp.GetPayload()	
	schemata.Set{{ pascalize $operationGroup }}ResourceData(d, respModel)
	d.Partial(false)

	return diags
}
	{{- end }}
	
	{{- if eq .Method "DELETE" }}
func {{ $operation }}(ctx context.Context, d *schema.ResourceData, m interface{}) diag.Diagnostics {
	var diags diag.Diagnostics
	
	params := {{ $operationGroup }}.New{{ pascalize $operation }}Params()

	{{ range .Params }}
		{{ camelize .ID }}Val, {{ camelize .ID }}IsSet := d.GetOk("{{ snakize .ID}}")
		if({{ camelize .ID }}IsSet){
			{{- if .IsPrimitive -}}
				{{- if eq (camelize .ID) "id" }}
					{{- template "stringToIntConversion" . -}}
				{{- else }}
					{{- if eq .GoType "int32" }} 
						i := int32({{ camelize .ID }}Val.(int))
						params.{{ pascalize .ID}} = {{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}{{ end }}{{ if not .IsFileParam }}{{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}&{{ end }}i{{ else }}runtime.NamedReadCloser{{- end -}}
					{{- else }}
						params.{{ pascalize .ID}} = {{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}{{ end }}{{ camelize .ID }}Val.({{ if not .IsFileParam }}{{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}*{{ end }}{{ .GoType }}{{ else }}runtime.NamedReadCloser{{ end }})
					{{- end }}
				{{- end }}
			{{- else }}
				params.{{ pascalize .ID}} = {{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}{{ end }}{{ camelize .ID }}Val.({{ if not .IsFileParam }}{{ if and (not .IsArray) (not .IsMap) (not .HasDiscriminator) (not .IsInterface) (not .IsStream) (or .IsNullable  ) }}*{{ end }}{{ .GoType }}{{ else }}runtime.NamedReadCloser{{ end }})
			{{- end }}
		} {{ if .Required }} else {
			diags = append(diags, diag.Errorf("unexpected: Missing parameter - {{ .Name }}")...)
			diags = append(diags, diag.Errorf("ending operation")...)
			return diags
		} {{ end }}
	{{ end }}

	{{ if .HasStreamingResponse }}
		var Discard io.Writer = io.Discard
	{{ end}}

	client := m.(*client.LogicMonitorRESTAPI)

	resp, err := client.{{ pascalize $operationGroup }}.{{ pascalize $operation }}(params {{ if .HasStreamingResponse }}, Discard {{ end }})
	log.Printf("[TRACE] response: %v", resp)
	if(err != nil){
		diags = append(diags, diag.Errorf("unexpected: %s", err)...)
		return diags
	}
	
	d.SetId("")
	return diags
}
	{{- end }}
	{{- end }}
{{ end }}

{{- if $isResource }}
func resource{{ pascalize $operationGroup }}StateImporter(d *schema.ResourceData, m interface{}) ([]*schema.ResourceData, error) {
	client := m.(*client.LogicMonitorRESTAPI)

	// if user provides an ID, we will add the {{ humanize $operationGroup }} directly
	if utils.IsID(d.Id()) {
		id, err := strconv.Atoi(d.Id())
		if err != nil {
			return nil, err
		}

		params := {{ $operationGroup }}.NewGet{{ pascalize $operationGroup }}ByIDParams()
		params.SetID(int32(id))
		
		resp, err := client.{{ pascalize $operationGroup }}.Get{{ pascalize $operationGroup }}ByID(params)
		log.Printf("[TRACE] response: %v", resp)
		if err != nil {
			log.Printf("Failed to find {{ humanize $operationGroup }} %q", err)
			return nil, err
		}

		respModel := resp.GetPayload()
		schemata.Set{{ pascalize $operationGroup }}ResourceData(d, respModel)
	} else {
		{{- if eq $operationGroup "collector" }}
		return nil, fmt.Errorf("collector must be imported with ID")

		{{- else }}
		// find {{ $operationGroup }} by name
		params := {{ $operationGroup }}.NewGet{{ pascalize $operationGroup }}ListParams()
		filter := fmt.Sprintf("{{- if eq $operationGroup "device_group" -}}fullPath{{- else -}}name{{- end -}}:\"%s\"", d.Id())
		params.SetFilter(&filter)

		resp, err := client.{{ pascalize $operationGroup }}.Get{{ pascalize $operationGroup }}List(params)
		log.Printf("[TRACE] response: %v", resp)
		if err != nil {
			err := fmt.Errorf("unexpected: %s", err)
			return nil, err
		}

		if resp.Payload.Total > 1 {
			err := fmt.Errorf("found more than 1 {{ humanize $operationGroup }} with filter %s, please make the filter more specific or import with ID", filter)
			return nil, err
		} else if resp.Payload.Total == 1 {
			schemata.Set{{ pascalize $operationGroup }}ResourceData(d, resp.Payload.Items[0])
			d.SetId(strconv.Itoa(int(resp.Payload.Items[0].ID)))
		} else {
			err := fmt.Errorf("found no {{ humanize $operationGroup }}s with filter '%s'", filter)
			return nil, err
		}
		{{- end }}
	}
	return []*schema.ResourceData{d}, nil
}
{{- end }}
