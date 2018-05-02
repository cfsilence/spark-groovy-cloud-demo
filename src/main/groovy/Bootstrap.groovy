import org.yaml.snakeyaml.Yaml

import static spark.Spark.*

class Bootstrap {

    static ConfigObject loadConfig(String environment) {
        def path = "${System.getProperty('confDir') ?: 'conf'}/config-${environment}.yml"
        def config
        if( environment == 'prod' ) {
            config = Bootstrap.class.getResourceAsStream(path)
        }
        else {
            config = new File(path).text
        }
        if( config ) {
            return new Yaml().load(config)
        }
        else {
            throw new Exception("Config file missing.\n\t-> You have attempted to load a config file from '${path}' but none was found.\n\t-> Please see 'config-template.yml',\n\t-> make a copy, rename it for this environment and populate it as necessary.")
        }
    }

    static void main(String[] args) {
        def passedEnv = args ? args[0] : 'prod'
        def environment = System.getProperty('environment') ?: passedEnv
        def config = loadConfig(environment)

        staticFileLocation('/public')
        staticFiles.expireTime(10)
        Integer p = System.getenv("PORT")?.toInteger() ?: 9000
        port(p)

        before "*", { req, res ->
            res.header("Access-Control-Allow-Headers", "Authorization, Content-Type");
            res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            res.header("Access-Control-Allow-Origin", "*");
        }

        options "*/*", { req, res ->

        }

	get "/", { req, res ->
	    return "hello again from ${environment}, now deployed from GitHub!"
	}
	
	get "/test", { req, res -> 
	    return "hi"
	}
    }

}
