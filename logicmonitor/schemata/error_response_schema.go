package schemata

import (
	"tf-provider-example/models"

	"github.com/hashicorp/terraform-plugin-sdk/v2/helper/schema"
)

func ErrorResponseSchema() map[string]*schema.Schema {
	return map[string]*schema.Schema{
		"error_code": {
			Type:     schema.TypeInt,
			Computed: true,
		},

		"error_detail": {
			Type: schema.TypeMap, //GoType: interface{}
			Elem: &schema.Schema{
				Type: schema.TypeString,
			},
			Computed: true,
		},

		"error_message": {
			Type:     schema.TypeString,
			Computed: true,
		},
	}
}

func SetErrorResponseResourceData(d *schema.ResourceData, m *models.ErrorResponse) {
	d.Set("error_code", m.ErrorCode)
	d.Set("error_detail", m.ErrorDetail)
	d.Set("error_message", m.ErrorMessage)
}

func SetErrorResponseSubResourceData(m []*models.ErrorResponse) (d []*map[string]interface{}) {
	for _, errorResponse := range m {
		if errorResponse != nil {
			properties := make(map[string]interface{})
			properties["error_code"] = errorResponse.ErrorCode
			properties["error_detail"] = errorResponse.ErrorDetail
			properties["error_message"] = errorResponse.ErrorMessage
			d = append(d, &properties)
		}
	}
	return
}

func ErrorResponseModel(d *schema.ResourceData) *models.ErrorResponse {

	return &models.ErrorResponse{}
}

func GetErrorResponsePropertyFields() (t []string) {
	return []string{}
}
