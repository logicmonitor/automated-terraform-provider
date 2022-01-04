package main

import (
	"context"
	"flag"
	"log"
	"terraform-provider-logicmonitor/logicmonitor"

	"github.com/hashicorp/terraform-plugin-sdk/v2/plugin"
	"github.com/hashicorp/terraform-plugin-sdk/v2/helper/schema"
)

func main() {
	opts := &plugin.ServeOpts{
		ProviderFunc: func() *schema.Provider {
			return logicmonitor.Provider()
		},
	}

	plugin.Serve(opts)
}
