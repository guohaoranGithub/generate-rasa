package com.miplus.generaterasa.Generator;

import com.miplus.generaterasa.config.BotConfig;
import org.springframework.stereotype.Component;

@Component
public class ChitchatWriter extends BotConfigWriter{
    @Override
    public String getNluContent(BotConfig config) {
        String nluConfig = "language: \"zh\"\n" +
                "pipeline:\n" +
                "  - name: \"WhitespaceTokenizer\"\n" +
                "  - name: \"CRFEntityExtractor\"\n" +
                "  - name: \"CountVectorsFeaturizer\"\n" +
                "    analyzer: \"char_wb\"\n" +
                "    min_ngram: 1\n" +
                "    max_ngram: 4\n" +
                "  - name: \"EmbeddingIntentClassifier\"\n";
        return nluConfig;
    }
}
