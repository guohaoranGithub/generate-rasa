package com.miplus.generaterasa.writer;

import com.miplus.generaterasa.config.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * 负责机器人配置文件的写入
 */
@Component
public class BotConfigWriter {

    /**
     * 读取文件内容，把每行数据顺序加到list的元素里
     *
     * @param filePath
     * @return
     */
    public List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }



    /**
     * 将list数据顺序写到文件里
     *
     * @param filePath
     * @param lines
     */
    public void write(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入配置文件
     *
     * @param content
     * @param filePath
     */
    public void write(String content, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入默认的rasa配置信息
     *
     * @param botConfig
     */
    public void writeDefaultBotConfig(BotConfig botConfig) {
        //项目根路径
        String projectDirectory = botConfig.getProjectDirectory();
        //写入config文件
        String configContent = this.getConfigContent(botConfig);
        String configPath = projectDirectory + "/config.yml";
        this.write(configContent, configPath);

        //写入credentials文件
        String credentialsContent = this.getCredentialsContent(botConfig);
        String credentialsPath = projectDirectory + "/credentials.yml";
        this.write(credentialsContent, credentialsPath);

        //写入domain文件
        String domainContent = this.getDomainContent(botConfig);
        String domainPath = projectDirectory + "/domain.yml";
        this.write(domainContent, domainPath);

        //写入endpoints文件
        String endpointsContent = this.getEndpointsContent(botConfig);
        String endpointsPath = projectDirectory + "/endpoints.yml";
        this.write(endpointsContent, endpointsPath);

        //写入nul文件
        String nluContent = this.getNluContent(botConfig);
        String nluPath = projectDirectory + "/data/" + "nlu.yml";
        this.write(nluContent, nluPath);

        //写入rules文件
        String rulesContent = this.getRulesContent(botConfig);
        String rulesPath = projectDirectory + "/data/" + "rules.yml";
        this.write(rulesContent, rulesPath);

        //写入stories文件
        String storiesContent = this.getStoriesContent(botConfig);
        String storiesPath = projectDirectory + "/data/" + "stories.yml";
        this.write(storiesContent, storiesPath);

        //写入responses文件
        String responsesContent = this.getResponsesContent(botConfig);
        String responsesPath = projectDirectory + "/data/" + "responses.yml";
        this.write(responsesContent, responsesPath);

        //requirements.txt
        String requirementsContent = this.getRequirementsContent();
        String rePath = projectDirectory + "/requirements.txt";
        this.write(requirementsContent, rePath);

        //启动文件
        String dockerContent = this.getDockerContent(botConfig);
        String dockerPath = projectDirectory + "/docker-compose.yml";
        this.write(dockerContent, dockerPath);
    }

    /**
     * 默认的responses配置
     *
     * @param botConfig
     * @return
     */
    private String getResponsesContent(BotConfig botConfig) {
        return "version: \"3.0\"\n" +
                "#responses:";
    }

    private String getRequirementsContent() {
        return "absl-py==0.13.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "aio-pika==6.8.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "aiofiles==22.1.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "aiogram==2.25.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "aiohttp==3.7.4 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "aiormq==3.3.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "aiosignal==1.3.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "APScheduler==3.7.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "astunparse==1.6.3 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "async-generator==1.10 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "async-timeout==3.0.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "attrs==21.2.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "Babel==2.9.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "bidict==0.22.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "boto3==1.24.96 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "botocore==1.27.96 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "CacheControl==0.12.11 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "cached-property==1.5.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "cachetools==4.2.4 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "certifi==2022.9.24 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "cffi==1.15.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "chardet==3.0.4 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "charset-normalizer==2.1.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "clang==5.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "cli-helpers==1.2.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "click==8.1.3 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "cloudpickle==1.6.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "colorclass==2.2.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "coloredlogs==15.0.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "colorhash==1.0.4 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "configobj==5.0.6 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "confluent-kafka==2.4.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "cryptography==38.0.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "cycler==0.11.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "dask==2021.11.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "decorator==5.1.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "dm-tree==0.1.7 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "dnspython==1.16.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "docopt==0.6.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "fbmessenger==6.0.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "filelock==3.8.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "fire==0.4.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "flatbuffers==1.12 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "frozenlist==1.4.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n"+
                "fsspec==2024.5.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "future==0.18.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "gast==0.4.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "google-auth==1.35.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "google-auth-oauthlib==0.4.6 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "google-pasta==0.2.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "greenlet==1.1.3.post0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "grpcio==1.50.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "h5py==3.1.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "httptools==0.5.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "huggingface-hub==0.23.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "humanfriendly==10.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "idna==3.4 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "importlib-metadata==5.0.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "iniconfig==1.1.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "jieba==0.42.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "jmespath==1.0.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "joblib==1.0.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "jsonpickle==2.0.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "jsonschema==3.2.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "kafka-python==2.0.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "keras==2.6.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "Keras-Preprocessing==1.1.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "kiwisolver==1.4.4 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "libclang==18.1.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "locket==1.0.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "magic-filter==1.0.12 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "Markdown==3.4.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "MarkupSafe==2.1.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "matplotlib==3.3.4 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "mattermostwrapper==2.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "MicroHMM==0.4.3 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "MicroTokenizer==0.21.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "msgpack==1.0.4 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "multidict==6.0.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "mycli==1.20.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "neo4j==4.4.8 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "networkx==2.6.3 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "numpy==1.19.5 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "oauthlib==3.2.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "opt-einsum==3.3.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "packaging==20.9 -i https://pypi.tuna.tsinghua.edu.cn/simple\n"+
                "pamqp==2.3.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "partd==1.3.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "Pillow==9.2.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "pluggy==1.0.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "portalocker==2.8.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "prompt-toolkit==2.0.10 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "protobuf==3.19.6 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "psycopg2-binary==2.9.4 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "py==1.11.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "pyasn1==0.4.8 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "pyasn1-modules==0.2.8 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "pycparser==2.21 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "pydantic==1.10.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "pydot==1.4.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "Pygments==2.6.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "PyJWT==2.6.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "pykwalify==1.8.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "pymongo==3.10.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "PyMySQL==0.9.3 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "pyparsing==3.0.9 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "pyrsistent==0.18.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "pyTelegramBotAPI==3.8.3 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "pytest==7.1.3 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "python-crfsuite==0.9.8 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "python-dateutil==2.8.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "python-engineio==4.3.4 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "python-socketio==5.7.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "pytz==2021.3 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "PyYAML==6.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "questionary==1.10.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "randomname==0.1.5 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "rasa==3.0.13 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "rasa-sdk==3.0.7 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "redis==3.5.3 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "regex==2021.8.28 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "requests==2.28.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "requests-oauthlib==1.3.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n"+
                "requests-toolbelt==0.10.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "rocketchat-API==1.16.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "rsa==4.9 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "ruamel.yaml==0.16.13 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "ruamel.yaml.clib==0.2.7 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "s3transfer==0.6.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "sacremoses==0.0.53 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "safetensors==0.4.3 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "sanic==21.12.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "Sanic-Cors==2.2.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "sanic-jwt==1.8.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "sanic-routing==0.7.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "scikit-learn==0.24.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "scipy==1.7.3 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "sentencepiece==0.1.97 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "sentry-sdk==1.3.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "six==1.16.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "sklearn-crfsuite==0.3.6 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "slack_sdk==3.27.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "slackclient==2.9.4 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "SQLAlchemy==1.4.42 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "sqlparse==0.3.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "structlog==23.3.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "structlog-sentry==2.1.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "tabulate==0.9.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "tarsafe==0.0.3 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "tensorboard==2.6.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "tensorboard-data-server==0.6.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "tensorboard-plugin-wit==1.8.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "tensorflow==2.6.5 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "tensorflow-addons==0.14.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "tensorflow-estimator==2.6.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "tensorflow-hub==0.12.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "tensorflow-io-gcs-filesystem==0.34.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "tensorflow-probability==0.13.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "tensorflow-text==2.6.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "termcolor==1.1.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "terminaltables==3.1.10 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "threadpoolctl==3.1.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "tokenizers==0.19.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "tomli==2.0.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "toolz==0.12.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "tqdm==4.64.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n"+
                "transformers==4.40.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "twilio==6.50.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "typeguard==2.13.3 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "typing-extensions==3.10.0.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "typing-utils==0.1.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "tzlocal==2.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "ujson==5.5.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "urllib3==1.26.12 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "uvloop==0.17.0 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "wcwidth==0.2.5 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "webexteamssdk==1.6.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "websockets==10.3 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "Werkzeug==2.2.2 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "wrapt==1.12.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "yarl==1.8.1 -i https://pypi.tuna.tsinghua.edu.cn/simple\n" +
                "zipp==3.9.0 -i https://pypi.tuna.tsinghua.edu.cn/simple";
    }

    /**
     * docker
     * @return
     */
    private String getDockerContent(BotConfig config) {

        return "version: '3.0'\n"+
                "services:\n"+
                "  "+config.getBotName()+"_rasa_service:\n"+
                "    image: rasa/rasa:3.0.13\n"+
                "    ports:\n"+
                "      - 5005:5005\n"+
                "    volumes:\n"+
                "      - ./:/app\n"+
                "    entrypoint: sh -c \"pip3 install -i https://pypi.tuna.tsinghua.edu.cn/simple --no-deps -r /app/requirements.txt --cache-dir=~/.pip/cache && rasa train && rasa run --enable-api\"\n"+
                "    depends_on:\n"+
                "      - "+config.getBotName()+"_action_server\n"+
                "    user: root\n\n"+
                "  "+config.getBotName()+"_action_server:\n"+
                "    image: rasa/rasa:3.0.13\n"+
                "    ports:\n"+
                "      - 5055:5055\n"+
                "    volumes:\n"+
                "      - ./:/app\n"+
                "    entrypoint: sh -c \"pip3 install -i https://pypi.tuna.tsinghua.edu.cn/simple --no-deps -r /app/requirements.txt --cache-dir=~/.pip/cache && rasa run actions\"\n"+
                "    user: root"
                ;
    }

    /**
     * 默认的nlu配置
     *
     * @param config
     * @return
     */
    public String getNluContent(BotConfig config) {
        NluConfig nluConfig = config.getNluConfig();
        HashMap<String, List<String>> intentMap = nluConfig.getIntentMap();

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("version: \"3.0\"\n");
        resultBuilder.append("nlu:\n");

        for (Map.Entry<String, List<String>> entry : intentMap.entrySet()) {
            String intent = entry.getKey();
            List<String> examples = entry.getValue();

            resultBuilder.append("  - intent: ").append(intent).append("\n");
            resultBuilder.append("    examples: |\n");

            for (String example : examples) {
                resultBuilder.append("      - ").append(example).append("\n");
            }
        }

        return resultBuilder.toString();
    }

    /**
     * 默认的stories配置
     *
     * @param config
     * @return
     */
    public String getStoriesContent(BotConfig config) {
        StoriesConfig storiesConfig = config.getStoriesConfig();
        LinkedHashMap<String, String> stepsMap = storiesConfig.getStepsMap();

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("version: \"3.0\"\n");
        resultBuilder.append("stories:\n");

        for (Map.Entry<String, String> entry : stepsMap.entrySet()) {
            String intent = entry.getKey();
            String action = entry.getValue();
            resultBuilder.append("  - story: ").append(intent).append("\n");
            resultBuilder.append("    steps:\n");
            resultBuilder.append("      - intent: ").append(intent).append("\n");
            resultBuilder.append("      - action: ").append(action).append("\n");
        }

        return resultBuilder.toString();
    }

    /**
     * 默认的rules配置
     *
     * @param config
     * @return
     */
    public String getRulesContent(BotConfig config) {
        RulesConfig rulesConfig = config.getRulesConfig();
        LinkedHashMap<String, String> stepsMap = rulesConfig.getStepsMap();

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("version: \"3.0\"\n");
        resultBuilder.append("rules:\n");
        for (Map.Entry<String, String> entry : stepsMap.entrySet()) {
            String intent = entry.getKey();
            String action = entry.getValue();
            resultBuilder.append("- rule: respond to ").append(intent).append("\n");
            resultBuilder.append("  steps:\n");
            resultBuilder.append("  - intent: ").append(intent).append("\n");
            resultBuilder.append("  - action: ").append(action).append("\n");
        }

        return resultBuilder.toString();
    }

    /**
     * 默认的domain配置
     *
     * @param config
     * @return
     */
    public String getDomainContent(BotConfig config) {
        DomainConfig domainConfig = config.getDomainConfig();

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("version: \"3.0\"\n\n");
        resultBuilder.append("session_config:\n");
        resultBuilder.append("  session_expiration_time: ")
                .append(domainConfig.getSessionConfig().getSessionExpirationTime()).append("\n");
        resultBuilder.append("  carry_over_slots_to_new_session: ")
                .append(domainConfig.getSessionConfig().getCarryOverSlotsToNewSession()).append("\n");
        List<String> intents = domainConfig.getIntents();
        resultBuilder.append("intents:\n");
        for (String intent : intents) {
            resultBuilder.append("  - ").append(intent).append("\n");
        }
        resultBuilder.append("responses:\n");
        LinkedHashMap<String, String> responsesMap = domainConfig.getResponsesMap();
        for (Map.Entry<String, String> entry : responsesMap.entrySet()) {
            String utter = entry.getKey();
            String text = entry.getValue();
            resultBuilder.append("  ").append(utter).append(":\n");
            resultBuilder.append("    - text: ").append(text).append("\n");
        }
        resultBuilder.append("actions:\n");
        List<String> actions = domainConfig.getActions();
        for (String action : actions) {
            resultBuilder.append("  - ").append(action).append("\n");
        }
        return resultBuilder.toString();
    }

    /**
     * config 默认配置
     *
     * @param config
     * @return
     */
    public String getConfigContent(BotConfig config) {
        return "recipe: default.v1\n" +
                "language: zh\n\n" +
                "pipeline:\n" +
                "  - name: JiebaTokenizer\n" +
                "  - name: LanguageModelFeaturizer\n" +
                "    model_name: bert\n" +
                "    model_weights: bert-base-chinese\n" +
                "  - name: DIETClassifier\n" +
                "    epochs: 100\n" +
                "    tensorboard_log_directory: ./log\n" +
                "    learning_rate: 0.001\n" +
                "  - name: FallbackClassifier\n" +
                "    threshold: 0.3\n" +
                "    ambiguity_threshold: 0.1\n" +
                "  - name: ResponseSelector\n" +
                "    epochs: 100\n"+
                "    retrieval_intent: faq\n"+
                "  - name: ResponseSelector\n" +
                "    epochs: 100\n"+
                "    retrieval_intent: chitchat\n"+
                "policies:\n" +
                "  - name: MemoizationPolicy\n" +
                "  - name: TEDPolicy\n" +
                "    max_history: 5\n" +
                "    epochs: 100\n" +
                "  - name: RulePolicy\n" +
                "    core_fallback_threshold: 0.3\n" +
                "    core_fallback_action_name: \"action_default_fallback\"\n" +
                "    enable_fallback_prediction: True\n";
    }

    /**
     * 默认的endpoints配置
     *
     * @param config
     * @return
     */
    public String getEndpointsContent(BotConfig config) {
        return "# This file contains the different endpoints your bot can use.\n\n" +
                "# Server where the models are pulled from.\n" +
                "# https://rasa.com/docs/rasa/user-guide/running-the-server/#fetching-models-from-a-server/\n\n" +
                "#models:\n" +
                "#  url: http://my-server.com/models/default_core@latest\n" +
                "#  wait_time_between_pulls:  10   # [optional](default: 100)\n\n" +
                "# Server which runs your custom actions.\n" +
                "# https://rasa.com/docs/rasa/core/actions/#custom-actions/\n\n" +
                "action_endpoint:\n" +
                "  url: \"http://localhost:5055/webhook\"\n\n" +
                "# Tracker store which is used to store the conversations.\n" +
                "# By default the conversations are stored in memory.\n" +
                "# https://rasa.com/docs/rasa/api/tracker-stores/\n\n" +
                "#tracker_store:\n" +
                "#    type: redis\n" +
                "#    url: <host of the redis instance, e.g. localhost>\n" +
                "#    port: <port of your redis instance, usually 6379>\n" +
                "#    db: <number of your database within redis, e.g. 0>\n" +
                "#    password: <password used for authentication>\n\n" +
                "#tracker_store:\n" +
                "#    type: mongod\n" +
                "#    url: <url to your mongo instance, e.g. mongodb://localhost:27017>\n" +
                "#    db: <name of the db within your mongo instance, e.g. rasa>\n" +
                "#    username: <username used for authentication>\n" +
                "#    password: <password used for authentication>\n\n" +
                "# Event broker which all conversation events should be streamed to.\n" +
                "# https://rasa.com/docs/rasa/api/event-brokers/\n\n" +
                "#event_broker:\n" +
                "#  url: localhost\n" +
                "#  username: username\n" +
                "#  password: password\n" +
                "#  queue: queue\n";
    }

    /**
     * 默认的credentials配置
     *
     * @param config
     * @return
     */
    public String getCredentialsContent(BotConfig config) {
        return "# This file contains the credentials for the voice & chat platforms\n" +
                "# which your bot is using.\n" +
                "# https://rasa.com/docs/rasa/user-guide/messaging-and-voice-channels/\n\n" +
                "rest:\n" +
                "#  # you don't need to provide anything here - this channel doesn't\n" +
                "#  # require any credentials\n\n\n" +
                "#facebook:\n" +
                "#  verify: \"<verify>\"\n" +
                "#  secret: \"<your secret>\"\n" +
                "#  page-access-token: \"<your page access token>\"\n\n" +
                "#slack:\n" +
                "#  slack_token: \"<your slack token>\"\n" +
                "#  slack_channel: \"<the slack channel>\"\n\n" +
                "socketio:\n" +
                "  user_message_evt: user_uttered\n" +
                "  bot_message_evt: bot_uttered\n" +
                "  session_persistence: false\n\n" +
                "rasa:\n" +
                "  url: \"http://localhost:5002/api\"";
    }
}
