run: format
	./mvnw spring-boot:run

test: format
	./mvnw test

format:
	./mvnw spotless:apply

clean:
	rm -rf target

clean-database:
	rm -f ./src/resources/h2/*.db

clean-uploads:
	rm -rf uploads

clean-all: clean clean-database clean-uploads

.PHONY: run test format clean clean-database clean-uploads clean-all
