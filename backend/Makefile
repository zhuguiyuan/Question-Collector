run:
	./mvnw spring-boot:run

clean:
	rm -rf target

clean-database:
	rm -f ./src/resources/h2/*.db

clean-uploads:
	rm -rf uploads

clean-all: clean clean-database clean-uploads

.PHONY: run clean clean-database clean-uploads clean-all
