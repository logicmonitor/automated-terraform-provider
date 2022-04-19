package schemata

import (
	"tf-provider-example/models"

	"github.com/hashicorp/terraform-plugin-sdk/v2/helper/schema"
)

// Schema mapping representing the ErrorResponse resource defined in the Terraform configuration
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

// Update the underlying ErrorResponse resource data in the Terraform configuration using the resource model built from the CREATE/UPDATE/READ LM API request response
func SetErrorResponseResourceData(d *schema.ResourceData, m *models.ErrorResponse) {
	d.Set("error_code", m.ErrorCode)
	d.Set("error_detail", m.ErrorDetail)
	d.Set("error_message", m.ErrorMessage)
}

// Iterate throught and update the ErrorResponse resource data within a pagination response (typically defined in the items array field) retrieved from a READ operation for multiple LM resources
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

// Function to perform the following actions:
// (1) Translate ErrorResponse resource data into a schema model struct that will sent to the LM API for resource creation/updating
// (2) Translate LM API response object from (1) or from a READ operation into a model that can be used to mofify the underlying resource data in the Terrraform configuration
func ErrorResponseModel(d *schema.ResourceData) *models.ErrorResponse {

	return &models.ErrorResponse{}
}

// Retrieve property field names for updating the ErrorResponse resource
func GetErrorResponsePropertyFields() (t []string) {
	return []string{}
}
