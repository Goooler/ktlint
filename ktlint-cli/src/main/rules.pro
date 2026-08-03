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

# Keep constructors across Kotlin compiler packages to ensure reflection-based instantiation works
-keep class org.jetbrains.kotlin.** {
    public <init>(...);
    protected <init>(...);
}

# Keep attributes required for Kotlin metadata and reflection
-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod,Exceptions,SourceFile,LineNumberTable

# Ignore missing optional dependencies / annotations warnings
-dontwarn **
