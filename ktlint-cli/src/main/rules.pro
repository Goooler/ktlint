# Entry point for CLI
-keep class com.pinterest.ktlint.Main {
    public static void main(java.lang.String[]);
}

# Keep CLI internals (including logging and exit helpers for integration tests)
-keep class com.pinterest.ktlint.cli.** { *; }

# Keep Kotlin Compiler PSI, AST, KDoc, and IntelliJ Platform classes used via reflection
-keep class org.jetbrains.kotlin.psi.** { *; }
-keep class org.jetbrains.kotlin.kdoc.** { *; }
-keep class org.jetbrains.kotlin.com.intellij.** { *; }
-keep class org.jetbrains.kotlin.KtNodeTypes { *; }
-keep class org.jetbrains.kotlin.KtStubBasedElementTypes { *; }
-keep class org.jetbrains.kotlin.compiler.plugin.** { *; }
-keep class org.jetbrains.kotlin.diagnostics.** { *; }

# Keep constructors for Kotlin Compiler PSI, KDoc, and IntelliJ Platform AST nodes instantiated via reflection
-keep class org.jetbrains.kotlin.psi.** {
    public <init>(...);
    protected <init>(...);
}
-keep class org.jetbrains.kotlin.kdoc.** {
    public <init>(...);
    protected <init>(...);
}
-keep class org.jetbrains.kotlin.com.intellij.** {
    public <init>(...);
    protected <init>(...);
}

# Keep attributes required for Kotlin metadata and reflection
-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod,Exceptions,SourceFile,LineNumberTable

# Ignore missing optional dependencies / annotations warnings
-dontwarn **
