package main

import (
	"tf-provider-example/logicmonitor"

	"github.com/hashicorp/terraform-plugin-sdk/v2/helper/schema"
	"github.com/hashicorp/terraform-plugin-sdk/v2/plugin"
)

func main() {
	opts := &plugin.ServeOpts{
		ProviderFunc: func() *schema.Provider {
			return logicmonitor.Provider()
		},
	}

	plugin.Serve(opts)
}
