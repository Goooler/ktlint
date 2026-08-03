# Entry point for CLI
-keep class com.pinterest.ktlint.Main {
    public static void main(java.lang.String[]);
}

# Preserve ServiceLoader implementations (bundled in submodule META-INF/proguard)

# Keep Rule implementations (subclasses of Rule) and their constructors
-keep class * extends com.pinterest.ktlint.rule.engine.core.api.Rule {
    public <init>(...);
    protected <init>(...);
}

# Keep Rule Engine public API and EditorConfig property definitions
-keep class com.pinterest.ktlint.rule.engine.api.** { *; }
-keep class com.pinterest.ktlint.rule.engine.core.api.editorconfig.** { *; }
-keep class com.pinterest.ktlint.cli.** { *; }

# Keep logging framework classes to preserve debug log output required by CommandLineTestRunner
-keep class io.github.oshai.kotlinlogging.** { *; }
-keep class org.slf4j.** { *; }
-keep class ch.qos.logback.** { *; }

# Keep Kotlin Compiler PSI, AST, KDoc, and IntelliJ Platform classes used via reflection
-keep class org.jetbrains.kotlin.psi.** { *; }
-keep class org.jetbrains.kotlin.kdoc.** { *; }
-keep class org.jetbrains.kotlin.com.intellij.** { *; }
-keep class org.jetbrains.kotlin.KtNodeTypes { *; }
-keep class org.jetbrains.kotlin.KtStubBasedElementTypes { *; }
-keep class org.jetbrains.kotlin.compiler.plugin.** { *; }
-keep class org.jetbrains.kotlin.diagnostics.** { *; }

# Keep constructors across Kotlin compiler packages to ensure reflection-based instantiation works
-keep class org.jetbrains.kotlin.** {
    public <init>(...);
    protected <init>(...);
}

# Keep attributes required for Kotlin metadata and reflection
-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod,Exceptions,SourceFile,LineNumberTable

# Ignore missing optional dependencies / annotations warnings
-dontwarn **
