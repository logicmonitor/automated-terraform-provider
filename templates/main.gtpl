package main

import (
	"tf-provider-example/logicmonitor"
)
{{/* Every TF provider needs a main function. Look how little it is, innit cute? */}}
func main() {
	opts := &plugin.ServeOpts{
		ProviderFunc: func() *schema.Provider {
			return logicmonitor.Provider()
		},
	}

	plugin.Serve(opts)
}
