# DDNS
This is an automatic ddns updater base on Springboot.

# How to use

## 1. Prepare a config file

Create a properties file e.g. `app.properties` contain below configs

```properties
# dns provider, only support cloudflare right now.
ddns.dns-provider=cloudflare
# your ddns domain
ddns.domain=test.cloudflare.com
ddns.interval=5
ddns.interval-unit=minutes

# zone id from cloudflare
ddns.cloudflare.zone-id=023e105f4ecef8ad9ca31a8372d0c353
# token to control zone setting
ddns.cloudflare.token=c2547eb745079dac9320b638f5e225cf483cc5cfdda41
```

## 2. Run application

There are 2 ways to run this application:
1. run via docker
2. run app.jar directly

### Run via docker

docker run -v PATH_TO_PROPERTIES_FILE:/opt/app.properties caltong/ddns

### Run app.jar directly

Try to clone this repo and test/build an app.jar

Run this command to start this app

```shell
java -jar PATH_TO_JAR_FILE --spring.config.location=PATH_TO_PROPERTIES_FILE
```