package builder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import jakarta.inject.Qualifier;

public class Build {

	@Qualifier
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ FIELD, METHOD })
	public @interface Built {}
}
