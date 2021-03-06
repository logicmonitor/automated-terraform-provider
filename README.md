# Automatically Writing a Terraform Provider for LogicMonitor

<img src="LM_logo.png" width="600">

<img src="https://www.datocms-assets.com/2885/1629941242-logo-terraform-main.svg" width="600px">
<br></br>

This is the companion code to our [**How to Write a Custom Terraform Provider Automatically With OpenAPI**](http://logicmonitor.com/blog/how-to-write-a-custom-terraform-provider-automatically-with-openapi) article.

Learn more by checking out the article!

## Requirements

-	[Terraform](https://www.terraform.io/downloads.html) 0.14.x
-	[Go](https://golang.org/doc/install) 1.16 (to build the provider plugin)
-   [Go-Swagger](https://goswagger.io/install.html) v0.27.0+ (to generate the code)

## Building the Provider
Clone repository (here, using SSH):
```sh
$ git clone git@github.com:logicmonitor/automated-terraform-provider.git
```
Enter the provider directory and build the provider:
```sh
$ cd automated-terraform-provider/
$ make
```
The Makefile will then generate the code, build the binary, and copy it to the Terraform plugin directory.

## Using the provider

The LogicMonitor Terraform Provider has two methods for setting required arguments:
Environment Variables
```sh
export LM_API_ID=xyz
export LM_API_KEY=xyz
export LM_COMPANY=xyz
```

Provider Initialization
```sh
provider "logicmonitor" {
  api_id = var.logicmonitor_api_id
  api_key = var.logicmonitor_api_key
  company = var.logicmonitor_company
}
```
Test cases can be found in the `/automated-terraform-provider/Test` directory.
