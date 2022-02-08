package logicmonitor 

import (
)

func Provider() *schema.Provider {
	return &schema.Provider{
		Schema: map[string]*schema.Schema{
			"api_id": {
				Type:        schema.TypeString,
				Required:    true,
				DefaultFunc: schema.EnvDefaultFunc("LM_API_ID", nil),
			},
			"api_key": {
				Type:        schema.TypeString,
				Required:    true,
				DefaultFunc: schema.EnvDefaultFunc("LM_API_KEY", nil),
			},
			"company": {
				Type:        schema.TypeString,
				Required:    true,
				DefaultFunc: schema.EnvDefaultFunc("LM_COMPANY", nil),
			},
		},
		ResourcesMap: map[string]*schema.Resource{
			{{- range .OperationGroups }}
			"logicmonitor_{{ humanize .Name | snakize }}": resources.{{ pascalize .Name }}(),
			{{- end }}
		},
		DataSourcesMap: map[string]*schema.Resource{
			{{- range .OperationGroups }}
			"logicmonitor_{{ humanize .Name | snakize }}": resources.DataResource{{ pascalize .Name }}(),
			{{- end }}
		},
		ConfigureContextFunc: providerConfigure,
	}
}

func providerConfigure(ctx context.Context, d *schema.ResourceData) (interface{}, diag.Diagnostics) {
	var diags diag.Diagnostics

 	id := d.Get("api_id").(string)
 	key := d.Get("api_key").(string)
 	company := d.Get("company").(string) + ".logicmonitor.com"
	config := client.NewConfig()
	config.SetAccessKey(&key)
	config.SetAccessID(&id)
	config.SetAccountDomain(&company)

	c := client.New(config)

	return c, diags
}
