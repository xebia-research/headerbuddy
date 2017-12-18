#!/bin/bash

# Set default values
URL=""
METHOD="get"
OUTPUT="json"
SPIDER="false"

# Check for flags
# u = URL, m = METHOD, o = OUTPUT, s = SPIDER
while getopts "u:m:o:s:" arg; do
  case $arg in
    u)
		URL=$OPTARG
		;;
    m)
		if [[ ${OPTARG,,} = "post" || ${OPTARG,,} = "get" ]]
		then
			METHOD=$OPTARG
		else
			echo "Invalid method. Using default get"
		fi
		;;
	o)
		if [[ ${OPTARG,,} = "json" || ${OPTARG,,} = "xml" ]]
		then		
			OUTPUT=$OPTARG
		else
			echo "Invalid output. Using default json"
		fi
		;;
	s)
		if [[ ${OPTARG,,} = "true" || ${OPTARG,,} = "false" ]] 
		then
			SPIDER=$OPTARG
		else
			echo "Invalid spider boolean. Using default false"
		fi
		;;
  esac
done

# Exit script if no URL is specified
if [ -z "$URL" ]
then
	echo "No URL specified"
	exit 
fi

# TODO: change key

# cURL on the REST API
HEADBUDDY=$(curl http://localhost:8080/headerbuddy/api?url=$URL"&"key=123"&"output=$OUTPUT"&"method=$METHOD"&"spider=$SPIDER)
echo "$HEADBUDDY" | json_pp