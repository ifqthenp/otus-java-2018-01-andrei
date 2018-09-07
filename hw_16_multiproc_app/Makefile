all: build

build: build_front build_back build_master

build_front:
	cd frontend && mvn clean package
build_back:
	cd backend && mvn clean package
build_master:
	cd master && mvn clean package
