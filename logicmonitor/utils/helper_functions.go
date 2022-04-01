package utils

import (
	"tf-provider-example/models"

	"github.com/hashicorp/terraform-plugin-sdk/v2/helper/schema"
)

// retrieve resource custom properties from resource structure
func GetPropertiesFromResource(d *schema.ResourceData, key string) (t []*models.NameAndValue) {
	if r, ok := d.GetOk(key); ok {
		return getPropertiesFromInterface(r)
	}
	return
}

func getPropertiesFromInterface(r interface{}) (t []*models.NameAndValue) {
	for _, i := range r.([]interface{}) {
		if m, ok := i.(map[string]interface{}); ok {
			var name = m["name"].(string)
			var value = m["value"].(string)
			model := &models.NameAndValue{
				Name:  &name,
				Value: &value,
			}
			t = append(t, model)
		}
	}
	return
}

// remove an item from array
func Remove(s []string, r string) []string {
	for i, v := range s {
		if v == r {
			return append(s[:i], s[i+1:]...)
		}
	}
	return s
}
