package schemata

import (
	"tf-provider-example/models"

	"github.com/hashicorp/terraform-plugin-sdk/v2/helper/schema"
)

// Schema mapping representing the NameAndValue resource defined in the Terraform configuration
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

// Update the underlying NameAndValue resource data in the Terraform configuration using the resource model built from the CREATE/UPDATE/READ LM API request response
func SetNameAndValueResourceData(d *schema.ResourceData, m *models.NameAndValue) {
	d.Set("name", m.Name)
	d.Set("value", m.Value)
}

// Iterate throught and update the NameAndValue resource data within a pagination response (typically defined in the items array field) retrieved from a READ operation for multiple LM resources
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

// Function to perform the following actions:
// (1) Translate NameAndValue resource data into a schema model struct that will sent to the LM API for resource creation/updating
// (2) Translate LM API response object from (1) or from a READ operation into a model that can be used to mofify the underlying resource data in the Terrraform configuration
func NameAndValueModel(d *schema.ResourceData) *models.NameAndValue {
	name := d.Get("name").(string)
	value := d.Get("value").(string)

	return &models.NameAndValue{
		Name:  &name,
		Value: &value,
	}
}

// Retrieve property field names for updating the NameAndValue resource
func GetNameAndValuePropertyFields() (t []string) {
	return []string{
		"name",
		"value",
	}
}
