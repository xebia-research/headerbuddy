#!/bin/sh

# Set default values
URL=""
METHOD="get"
OUTPUT="json"
SPIDER="false"
APIKEY=""

# Check for flags and change the default values. All whitespace is removed.
# u = URL, m = METHOD, o = OUTPUT, s = SPIDER k = API key h = help
while getopts "u:m:o:s:k:h" arg; do
	case $arg in
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
		h)
			echo -e "Headerbuddy. A HTTP header checker API.\n\nOptions:
			\n-u [string]\tSets the URL (Required)
			\n-k [string]\tSet the API key (Required)
			\n-m [string]\tSets the HTTP method (default = get | post | trace | put | delete | connect | head | options | patch | all)
			\n-o [string]\tSets the output type (default = json | xml | html)
			\n-s [string]\tEnable domain spidering (default = false | true)
			"
			exit
		;;
	esac
done

# Exit script if no URL or API key is specified
if [[ -z "$URL" || -z "$APIKEY" ]]
then
	if [ -z "$URL" ]
	then
		echo -e "No URL specified. Use the -u [string] option."
	fi
	
	if [ -z "$APIKEY" ]
	then
		echo -e "No API key specified. Use the -k [string] option."
	fi
	
	echo "Run \"headerbuddy.sh -h\" for more information."
	
	exit 
fi

# cURL on the REST API
HEADERBUDDY=$(curl http://localhost:8080/headerbuddy/api?url=$URL"&"key=$APIKEY"&"output=$OUTPUT"&"method=$METHOD"&"spider=$SPIDER)

# Pretty print json or xml 
case $OUTPUT in 
	"json")
		echo "$HEADERBUDDY" | json_pp 
		;;
	"xml")
		echo "$HEADERBUDDY"
		;;
esac
		

exit 