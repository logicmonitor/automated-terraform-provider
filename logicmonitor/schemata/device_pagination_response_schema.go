package schemata

import (
	"tf-provider-example/models"

	"github.com/hashicorp/terraform-plugin-sdk/v2/helper/schema"
)

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

func SetDevicePaginationResponseResourceData(d *schema.ResourceData, m *models.DevicePaginationResponse) {
	d.Set("items", SetDeviceSubResourceData(m.Items))
	d.Set("search_id", m.SearchID)
	d.Set("total", m.Total)
}

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

func DevicePaginationResponseModel(d *schema.ResourceData) *models.DevicePaginationResponse {
	items := d.Get("items").([]*models.Device)

	return &models.DevicePaginationResponse{
		Items: items,
	}
}

func GetDevicePaginationResponsePropertyFields() (t []string) {
	return []string{
		"items",
	}
}
