package root.com.push;

import com.tencent.xinge.bean.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class PushConfig {

    @Value("${ENV:dev}")
    private String env;

    @Bean
    CustomPushApp androidPush() {
        return new CustomPushApp("e8ba2eddbcce1", "6af3b623fb98504b46ea649da91864ce");
    }

    @Bean
    CustomPushApp iOSPush() {
        return new CustomPushApp("d1e0f9629a187",
                "2475626e021c7ed664b7bdfaafe740e4",
                Objects.equals(env, "dev") ? Environment.dev : Environment.product);
    }

}
