package utils

import (
)

// convert string ID to floating point number
func IsID(s string) bool {
	_, err := strconv.ParseFloat(s, 64)
	return err == nil
}

// retrieve resource custom properties from map structure
func GetPropertiesFromMap(d map[string]interface{}, key string) (t []*models.NameAndValue) {
	if r, ok := d[key]; ok {
		return getPropertiesFromInterface(r)
	}
	return
}

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

// retrieve resource custom properties (techops version - json object input)
func GetPropertiesTechops(d *schema.ResourceData) (t []*models.NameAndValue) {
	// interate through hashmap to get custom/system properties
	if r, ok := d.GetOk("custom_properties"); ok {
		for k, v := range r.(map[string]interface{}) {
			key := k
			value, _ := v.(string)
			t = append(t, &models.NameAndValue{Name: &key, Value: &value})
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

func ConvertSetToStringSlice(set *schema.Set) (slice []string) {
	if set == nil {
		return
	}
	setList := set.List()
	slice = make([]string, len(setList))
	for i, v := range setList {
		slice[i] = v.(string)
	}
	return
}
