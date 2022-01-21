package main

import (
	"tf-provider-example/logicmonitor"
)

func main() {
	opts := &plugin.ServeOpts{
		ProviderFunc: func() *schema.Provider {
			return logicmonitor.Provider()
		},
	}

	plugin.Serve(opts)
}
