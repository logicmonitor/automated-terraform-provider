package schemata

import (
	"tf-provider-example/models"

	"github.com/hashicorp/terraform-plugin-sdk/v2/helper/schema"
)

// Schema mapping representing the DevicePaginationResponse resource defined in the Terraform configuration
func DevicePaginationResponseSchema() map[string]*schema.Schema {
	return map[string]*schema.Schema{
		"items": {
			Type: schema.TypeList, //GoType: []*Device
			Elem: &schema.Resource{
				Schema: DeviceSchema(),
			},
			ConfigMode: schema.SchemaConfigModeAttr,
			Optional:   true,
		},

		"search_id": {
			Type:     schema.TypeString,
			Computed: true,
		},

		"total": {
			Type:     schema.TypeInt,
			Computed: true,
		},
	}
}

// Update the underlying DevicePaginationResponse resource data in the Terraform configuration using the resource model built from the CREATE/UPDATE/READ LM API request response
func SetDevicePaginationResponseResourceData(d *schema.ResourceData, m *models.DevicePaginationResponse) {
	d.Set("items", SetDeviceSubResourceData(m.Items))
	d.Set("search_id", m.SearchID)
	d.Set("total", m.Total)
}

// Iterate throught and update the DevicePaginationResponse resource data within a pagination response (typically defined in the items array field) retrieved from a READ operation for multiple LM resources
func SetDevicePaginationResponseSubResourceData(m []*models.DevicePaginationResponse) (d []*map[string]interface{}) {
	for _, devicePaginationResponse := range m {
		if devicePaginationResponse != nil {
			properties := make(map[string]interface{})
			properties["items"] = SetDeviceSubResourceData(devicePaginationResponse.Items)
			properties["search_id"] = devicePaginationResponse.SearchID
			properties["total"] = devicePaginationResponse.Total
			d = append(d, &properties)
		}
	}
	return
}

// Function to perform the following actions:
// (1) Translate DevicePaginationResponse resource data into a schema model struct that will sent to the LM API for resource creation/updating
// (2) Translate LM API response object from (1) or from a READ operation into a model that can be used to mofify the underlying resource data in the Terrraform configuration
func DevicePaginationResponseModel(d *schema.ResourceData) *models.DevicePaginationResponse {
	items := d.Get("items").([]*models.Device)

	return &models.DevicePaginationResponse{
		Items: items,
	}
}

// Retrieve property field names for updating the DevicePaginationResponse resource
func GetDevicePaginationResponsePropertyFields() (t []string) {
	return []string{
		"items",
	}
}
