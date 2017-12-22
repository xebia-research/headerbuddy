#!/bin/sh

# Set default values
URL=""
METHOD="get"
OUTPUT="json"
SPIDER="false"
APIKEY=""
PORT=8080
IP="localhost"

# Check for flags and change the default values. All whitespace is removed.
# u = URL, m = METHOD, o = OUTPUT, s = SPIDER k = API key p = port i = ip h = help
while getopts "a:u:m:o:s:k:p:i:h" arg; do
	case $arg in
		a)
			EMAIL=${OPTARG//[[:blank:]]/}

			if [ ! -z "$EMAIL" ]
			then
				GENERATEDKEY=$(curl http://$IP:$PORT/headerbuddy/key?email=$EMAIL)
				echo "$GENERATEDKEY";
			else
				echo "Email is not valid."
				exit
			fi
			;;
		u)
			URL=${OPTARG//[[:blank:]]/}
			;;
		m)
			METHOD=${OPTARG//[[:blank:]]/}
			;;
		o)
			OUTPUT=${OPTARG//[[:blank:]]/}
			;;
		s)
			SPIDER=${OPTARG//[[:blank:]]/}
			;;
		k)
			APIKEY=${OPTARG//[[:blank:]]/}
			;;
		p)
			PORT=$OPTARG
			;;
		i)
			IP=$OPTARG
			;;
		h)
			echo -e "Headerbuddy. A HTTP header checker API.\n\nOptions:
			\n-u [string]\tSets the URL. (Required)
			\n-k [string]\tSets the API key. (Required)
			\n-a [string]\tRequest an API key.
			\n-m [string]\tSets the HTTP method. (default = get | post | trace | put | delete | connect | head | options | patch | all)
			\n-o [string]\tSets the output type. (default = json | xml)
			\n-s [string]\tEnable domain spidering. (default = false | true)
			\n-p [integers]\tSets custom port to API. (default = 8080)
			\n-i [string | integers]\tSets a custom IP to the API. (default = localhost)
			"
			exit
		;;
	esac
done

# Exit script if no URL or API key is specified or if the provided port is no integer.
if [ -z "$GENERATEDKEY" ]
then
	if [[ -z "$URL" || -z "$APIKEY" || "$PORT" =~ "^[0-9]+$" ]]
	then
		if [ -z "$URL" ]
		then
			echo -e "No URL specified. Use the -u [string] option."
		fi

		if [ -z "$APIKEY" ]
		then
			echo -e "No API key specified. Use the -k [string] option."
		fi

		if ! [[ "$PORT" =~ ^[0-9]+$ ]]
		then
			echo -e "Value of the custom port is not valid. Only integers are allowed for -p [integers]."
		fi

		echo "Run \"./headerbuddy.sh -h\" for more information."

		exit
	fi

	# cURL on the REST API
	HEADERBUDDY=$(curl http://$IP:$PORT/headerbuddy/api?url=$URL"&"key=$APIKEY"&"output=$OUTPUT"&"method=$METHOD"&"spider=$SPIDER)

	# Pretty print json or xml
	case $OUTPUT in
		"json")
			echo "$HEADERBUDDY" | json_pp
			;;
		"xml")
			echo "$HEADERBUDDY"
			;;
	esac
fi



exit 