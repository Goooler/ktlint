package com.pinterest.ktlint.ruleset.standard.rules

import com.pinterest.ktlint.rule.engine.core.api.AutocorrectDecision
import com.pinterest.ktlint.rule.engine.core.api.ElementType.IMPORT_LIST
import com.pinterest.ktlint.rule.engine.core.api.ElementType.PACKAGE_DIRECTIVE
import com.pinterest.ktlint.rule.engine.core.api.ElementType.SCRIPT
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import com.pinterest.ktlint.rule.engine.core.api.SinceKtlint
import com.pinterest.ktlint.rule.engine.core.api.SinceKtlint.Status.EXPERIMENTAL
import com.pinterest.ktlint.rule.engine.core.api.SinceKtlint.Status.STABLE
import com.pinterest.ktlint.rule.engine.core.api.children20
import com.pinterest.ktlint.rule.engine.core.api.isCode
import com.pinterest.ktlint.rule.engine.core.api.isRoot20
import com.pinterest.ktlint.ruleset.standard.StandardRule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

@SinceKtlint("0.50", EXPERIMENTAL)
@SinceKtlint("1.0", STABLE)
public class NoEmptyFileRule : StandardRule(id = "no-empty-file") {
    override fun beforeVisitChildNodes(
        node: ASTNode,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> AutocorrectDecision,
    ) {
        node
            .takeIf { it.isRoot20 }
            ?.takeIf { it.isEmptyFile() }
            ?.let { emit(0, "File '${node.getFileName()}' should not be empty", false) }
    }

    private fun ASTNode.getFileName() =
        psi
            .containingFile
            .virtualFile
            .name
            .replace("\\", "/") // Ensure compatibility with Windows OS
            .substringAfterLast("/")

    private fun ASTNode.isEmptyFile(): Boolean =
        null ==
            children20
                .firstOrNull {
                    it.isCode &&
                        it.elementType != PACKAGE_DIRECTIVE &&
                        it.elementType != IMPORT_LIST &&
                        !(it.elementType == SCRIPT && it.text.isBlank())
                }
}

public val NO_EMPTY_FILE_RULE_ID: RuleId = NoEmptyFileRule().ruleId
