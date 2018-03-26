import java.util.List;
import java.util.Properties;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.ArrayCoreMap;
import edu.stanford.nlp.util.CoreMap;

public class NLPAnalysis {
static StanfordCoreNLP pipeline;

    public static void init() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        pipeline = new StanfordCoreNLP(props);
    }

    public static String findSentiment(String tweet) {
        String SentiReturn = "";
        String[] SentiClass ={"very negative", "negative", "neutral", "positive", "very positive"};

        //Sentiment is an integer, ranging from 0 to 4. 
        //0 is very negative, 1 negative, 2 neutral, 3 positive and 4 very positive.
        int sentiment = 2;

        if (tweet != null && tweet.length() > 0) {
            Annotation annotation = pipeline.process(tweet);

            List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
            if (sentences != null && sentences.size() > 0) {

                ArrayCoreMap sentence = (ArrayCoreMap) sentences.get(0);                
                Tree tree = sentence.get(SentimentAnnotatedTree.class);  
                sentiment = RNNCoreAnnotations.getPredictedClass(tree);             
                SentiReturn = SentiClass[sentiment];
            }
        }
        return SentiReturn;
    }

}
