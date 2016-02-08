# SELENIUM-GRID + DOCKER-COMPOSE

That is an example of how to use selenium-grid + docker-compose + JUnit to run tests in parallel.

## Running

Steps necessary to put your default machine up and to pull the containers of docker-compose.yml

- `docker-machine start default`

- `eval "$(docker-machine env default)"`

- `docker-compose up`

To check if is everything ok, you should see an output in your terminal like this:
`INFO - Selenium Grid hub is up and running`

After this, you must enter on selenium-grid console in your browser, following these steps:

- `docker-machine env default`

This command will show the ip of your default machine on line `export DOCKER_HOST`.
Now, enter in your browser and enter your host in port 4444.

-  `http://XXX.XXX.XX.XXX:4444/grid/console`

Now, to run the tests in parallel on the selenium grid, just put the maven command to run the tests: 

- `mvn test`