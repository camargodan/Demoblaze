package runner;

import com.intuit.karate.junit5.Karate;

public class DemoblazeRunner {
    
    @Karate.Test
    Karate testAuth() {
        return Karate.run("classpath:features/auth.feature")
                .relativeTo(getClass());
    }
    
    @Karate.Test
    Karate testSignup() {
        return Karate.run("classpath:features/auth.feature")
                .tags("@signup")
                .relativeTo(getClass());
    }
    
    @Karate.Test
    Karate testLogin() {
        return Karate.run("classpath:features/auth.feature")
                .tags("@login")
                .relativeTo(getClass());
    }
}
