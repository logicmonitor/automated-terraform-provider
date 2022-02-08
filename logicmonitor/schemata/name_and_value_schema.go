package schemata

import (
	"tf-provider-example/models"

	"github.com/hashicorp/terraform-plugin-sdk/v2/helper/schema"
)

func NameAndValueSchema() map[string]*schema.Schema {
	return map[string]*schema.Schema{
		"name": {
			Type:     schema.TypeString,
			Required: true,
		},

		"value": {
			Type:     schema.TypeString,
			Required: true,
		},
	}
}

func SetNameAndValueResourceData(d *schema.ResourceData, m *models.NameAndValue) {
	d.Set("name", m.Name)
	d.Set("value", m.Value)
}

func SetNameAndValueSubResourceData(m []*models.NameAndValue) (d []*map[string]interface{}) {
	for _, nameAndValue := range m {
		if nameAndValue != nil {
			properties := make(map[string]interface{})
			properties["name"] = nameAndValue.Name
			properties["value"] = nameAndValue.Value
			d = append(d, &properties)
		}
	}
	return
}

func NameAndValueModel(d *schema.ResourceData) *models.NameAndValue {
	name := d.Get("name").(string)
	value := d.Get("value").(string)

	return &models.NameAndValue{
		Name:  &name,
		Value: &value,
	}
}

func GetNameAndValuePropertyFields() (t []string) {
	return []string{
		"name",
		"value",
	}
}
