-keep class * implements com.pinterest.ktlint.rule.engine.core.api.RuleProvider { *; }

-keep class com.pinterest.ktlint.rule.engine.core.api.RuleProvider {
    public static final com.pinterest.ktlint.rule.engine.core.api.RuleProvider$Companion Companion;
}

-keep class com.pinterest.ktlint.rule.engine.core.api.RuleProvider$Companion { *; }

-keepclassmembers class com.pinterest.ktlint.rule.engine.core.api.RuleProvider** {
    public *;
}

-keep class * extends com.pinterest.ktlint.rule.engine.core.api.Rule {
    public <init>(...);
    protected <init>(...);
}

-keep class com.pinterest.ktlint.rule.engine.core.api.LoggerFactory {
    public <init>();
}

-keep class com.pinterest.ktlint.rule.engine.core.api.editorconfig.** { *; }

-keep class com.pinterest.ktlint.rule.engine.core.api.** {
    public <init>(...);
    protected <init>(...);
}

# Keep Kotlin standard library classes accessed by dynamic custom ruleset jars
-keep class kotlin.collections.** { *; }
-keep class kotlin.sequences.** { *; }
-keep class kotlin.text.** { *; }
-keep class kotlin.io.** { *; }
-keep class kotlin.jvm.internal.** { *; }
-keep class kotlin.jvm.functions.** { *; }
