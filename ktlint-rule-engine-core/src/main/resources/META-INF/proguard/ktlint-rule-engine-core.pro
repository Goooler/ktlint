-keep class * implements com.pinterest.ktlint.rule.engine.core.api.RuleProvider { *; }
-keep class com.pinterest.ktlint.rule.engine.core.api.RuleProvider$Companion { *; }

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
