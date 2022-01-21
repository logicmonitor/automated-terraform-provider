// Code generated by go-swagger; DO NOT EDIT.

package device

// This file was generated by the swagger tool.
// Editing this file might prove futile when you re-run the swagger generate command

import (
	"context"
	"net/http"
	"time"

	"github.com/go-openapi/errors"
	"github.com/go-openapi/runtime"
	cr "github.com/go-openapi/runtime/client"
	"github.com/go-openapi/strfmt"
	"github.com/go-openapi/swag"

	"tf-provider-example/models"
)

// NewUpdateDeviceParams creates a new UpdateDeviceParams object,
// with the default timeout for this client.
//
// Default values are not hydrated, since defaults are normally applied by the API server side.
//
// To enforce default values in parameter, use SetDefaults or WithDefaults.
func NewUpdateDeviceParams() *UpdateDeviceParams {
	return &UpdateDeviceParams{
		timeout: cr.DefaultTimeout,
	}
}

// NewUpdateDeviceParamsWithTimeout creates a new UpdateDeviceParams object
// with the ability to set a timeout on a request.
func NewUpdateDeviceParamsWithTimeout(timeout time.Duration) *UpdateDeviceParams {
	return &UpdateDeviceParams{
		timeout: timeout,
	}
}

// NewUpdateDeviceParamsWithContext creates a new UpdateDeviceParams object
// with the ability to set a context for a request.
func NewUpdateDeviceParamsWithContext(ctx context.Context) *UpdateDeviceParams {
	return &UpdateDeviceParams{
		Context: ctx,
	}
}

// NewUpdateDeviceParamsWithHTTPClient creates a new UpdateDeviceParams object
// with the ability to set a custom HTTPClient for a request.
func NewUpdateDeviceParamsWithHTTPClient(client *http.Client) *UpdateDeviceParams {
	return &UpdateDeviceParams{
		HTTPClient: client,
	}
}

/* UpdateDeviceParams contains all the parameters to send to the API endpoint
   for the update device operation.

   Typically these are written to a http.Request.
*/
type UpdateDeviceParams struct {

	// Body.
	Body *models.Device

	// End.
	//
	// Format: int64
	End *int64

	// ID.
	//
	// Format: int32
	ID int32

	// NetflowFilter.
	NetflowFilter *string

	// OpType.
	//
	// Default: "refresh"
	OpType *string

	// Start.
	//
	// Format: int64
	Start *int64

	timeout    time.Duration
	Context    context.Context
	HTTPClient *http.Client
}

// WithDefaults hydrates default values in the update device params (not the query body).
//
// All values with no default are reset to their zero value.
func (o *UpdateDeviceParams) WithDefaults() *UpdateDeviceParams {
	o.SetDefaults()
	return o
}

// SetDefaults hydrates default values in the update device params (not the query body).
//
// All values with no default are reset to their zero value.
func (o *UpdateDeviceParams) SetDefaults() {
	var (
		opTypeDefault = string("refresh")
	)

	val := UpdateDeviceParams{
		OpType: &opTypeDefault,
	}

	val.timeout = o.timeout
	val.Context = o.Context
	val.HTTPClient = o.HTTPClient
	*o = val
}

// WithTimeout adds the timeout to the update device params
func (o *UpdateDeviceParams) WithTimeout(timeout time.Duration) *UpdateDeviceParams {
	o.SetTimeout(timeout)
	return o
}

// SetTimeout adds the timeout to the update device params
func (o *UpdateDeviceParams) SetTimeout(timeout time.Duration) {
	o.timeout = timeout
}

// WithContext adds the context to the update device params
func (o *UpdateDeviceParams) WithContext(ctx context.Context) *UpdateDeviceParams {
	o.SetContext(ctx)
	return o
}

// SetContext adds the context to the update device params
func (o *UpdateDeviceParams) SetContext(ctx context.Context) {
	o.Context = ctx
}

// WithHTTPClient adds the HTTPClient to the update device params
func (o *UpdateDeviceParams) WithHTTPClient(client *http.Client) *UpdateDeviceParams {
	o.SetHTTPClient(client)
	return o
}

// SetHTTPClient adds the HTTPClient to the update device params
func (o *UpdateDeviceParams) SetHTTPClient(client *http.Client) {
	o.HTTPClient = client
}

// WithBody adds the body to the update device params
func (o *UpdateDeviceParams) WithBody(body *models.Device) *UpdateDeviceParams {
	o.SetBody(body)
	return o
}

// SetBody adds the body to the update device params
func (o *UpdateDeviceParams) SetBody(body *models.Device) {
	o.Body = body
}

// WithEnd adds the end to the update device params
func (o *UpdateDeviceParams) WithEnd(end *int64) *UpdateDeviceParams {
	o.SetEnd(end)
	return o
}

// SetEnd adds the end to the update device params
func (o *UpdateDeviceParams) SetEnd(end *int64) {
	o.End = end
}

// WithID adds the id to the update device params
func (o *UpdateDeviceParams) WithID(id int32) *UpdateDeviceParams {
	o.SetID(id)
	return o
}

// SetID adds the id to the update device params
func (o *UpdateDeviceParams) SetID(id int32) {
	o.ID = id
}

// WithNetflowFilter adds the netflowFilter to the update device params
func (o *UpdateDeviceParams) WithNetflowFilter(netflowFilter *string) *UpdateDeviceParams {
	o.SetNetflowFilter(netflowFilter)
	return o
}

// SetNetflowFilter adds the netflowFilter to the update device params
func (o *UpdateDeviceParams) SetNetflowFilter(netflowFilter *string) {
	o.NetflowFilter = netflowFilter
}

// WithOpType adds the opType to the update device params
func (o *UpdateDeviceParams) WithOpType(opType *string) *UpdateDeviceParams {
	o.SetOpType(opType)
	return o
}

// SetOpType adds the opType to the update device params
func (o *UpdateDeviceParams) SetOpType(opType *string) {
	o.OpType = opType
}

// WithStart adds the start to the update device params
func (o *UpdateDeviceParams) WithStart(start *int64) *UpdateDeviceParams {
	o.SetStart(start)
	return o
}

// SetStart adds the start to the update device params
func (o *UpdateDeviceParams) SetStart(start *int64) {
	o.Start = start
}

// WriteToRequest writes these params to a swagger request
func (o *UpdateDeviceParams) WriteToRequest(r runtime.ClientRequest, reg strfmt.Registry) error {

	if err := r.SetTimeout(o.timeout); err != nil {
		return err
	}
	var res []error
	if o.Body != nil {
		if err := r.SetBodyParam(o.Body); err != nil {
			return err
		}
	}

	if o.End != nil {

		// query param end
		var qrEnd int64

		if o.End != nil {
			qrEnd = *o.End
		}
		qEnd := swag.FormatInt64(qrEnd)
		if qEnd != "" {

			if err := r.SetQueryParam("end", qEnd); err != nil {
				return err
			}
		}
	}

	// path param id
	if err := r.SetPathParam("id", swag.FormatInt32(o.ID)); err != nil {
		return err
	}

	if o.NetflowFilter != nil {

		// query param netflowFilter
		var qrNetflowFilter string

		if o.NetflowFilter != nil {
			qrNetflowFilter = *o.NetflowFilter
		}
		qNetflowFilter := qrNetflowFilter
		if qNetflowFilter != "" {

			if err := r.SetQueryParam("netflowFilter", qNetflowFilter); err != nil {
				return err
			}
		}
	}

	if o.OpType != nil {

		// query param opType
		var qrOpType string

		if o.OpType != nil {
			qrOpType = *o.OpType
		}
		qOpType := qrOpType
		if qOpType != "" {

			if err := r.SetQueryParam("opType", qOpType); err != nil {
				return err
			}
		}
	}

	if o.Start != nil {

		// query param start
		var qrStart int64

		if o.Start != nil {
			qrStart = *o.Start
		}
		qStart := swag.FormatInt64(qrStart)
		if qStart != "" {

			if err := r.SetQueryParam("start", qStart); err != nil {
				return err
			}
		}
	}

	if len(res) > 0 {
		return errors.CompositeValidationError(res...)
	}
	return nil
}
