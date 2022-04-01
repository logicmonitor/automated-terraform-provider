HOSTNAME=logicmonitor.com
NAMESPACE=com
NAME=logicmonitor
BINARY=terraform-provider-${NAME}
VERSION=0.2
OS_ARCH=darwin_amd64

default: install

clean:
	rm -Rf data_profile/
	rm -Rf logicmonitor/*

build:  clean
	swagger generate client -f ./spec_files/current.json --template-dir templates -C config.yml > swagrun.log
	go build -o ${BINARY}

install: build
	mkdir -p ~/.terraform.d/plugins/${HOSTNAME}/${NAMESPACE}/${NAME}/${VERSION}/${OS_ARCH}
	cp ${BINARY} ~/.terraform.d/plugins/${HOSTNAME}/${NAMESPACE}/${NAME}/${VERSION}/${OS_ARCH}

nogen: 
	go build -o ${BINARY}
	mkdir -p ~/.terraform.d/plugins/${HOSTNAME}/${NAMESPACE}/${NAME}/${VERSION}/${OS_ARCH}
	mv ${BINARY} ~/.terraform.d/plugins/${HOSTNAME}/${NAMESPACE}/${NAME}/${VERSION}/${OS_ARCH}