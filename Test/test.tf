terraform {
	required_version = ">= 0.12.00"
	required_providers {
		logicmonitor = {
			source  = "logicmonitor.com/com/logicmonitor"
			version = "0.1"
		}
	}
}

# provider "logicmonitor" {
# 	api_id = <ADD YOUR LM API_ID HERE>
# 	api_key = <ADD YOUR LM API_KEY HERE>
# 	company = <ADD YOUR LM PORTAL NAME HERE>
# }

provider "logicmonitor" {
	api_id = "iG9utmsvgZCpQzwEg2Mf"
	api_key = "v8%QzY]Z[+d6-y~8K=7[6mLW+~)d{YwT]3Nn3m{S"
	company = "lmadamjohnson"
}

resource "logicmonitor_device" "my_device"{
	preferred_collector_id = 1
	custom_properties = [
		{
			name = "addr"
      		value = "127.0.0.1"
		},
		{
			name = "host"
      		value = "localhost"
		}
	]
	description = "This is a Cisco Router"
	device_type  = 0
	disable_alerting = true
	display_name = "Cisco Router"
	enable_netflow = false
	link = "www.ciscorouter.com"
	name = "collector.host"
}

data "logicmonitor_device" "my_devices" {
    filter = "displayName~\"Cisco Router\""
	depends_on = [
		logicmonitor_device.my_device
	]
}

output "devices" {
  description = "devices list"
  value       = data.logicmonitor_device.my_devices
}