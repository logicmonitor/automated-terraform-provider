default: build

clean:
	-rm .terraform.lock.hcl
	terraform init

plan:
	terraform plan -out=lm.tfstate

build: clean plan apply

apply:
	terraform apply lm.tfstate