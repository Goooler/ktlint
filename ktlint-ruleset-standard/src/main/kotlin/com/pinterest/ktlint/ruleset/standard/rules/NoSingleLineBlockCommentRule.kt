package com.pinterest.ktlint.ruleset.standard.rules

import com.pinterest.ktlint.rule.engine.core.api.AutocorrectDecision
import com.pinterest.ktlint.rule.engine.core.api.ElementType.BLOCK_COMMENT
import com.pinterest.ktlint.rule.engine.core.api.ElementType.EOL_COMMENT
import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.Rule.VisitorModifier.RunAfterRule
import com.pinterest.ktlint.rule.engine.core.api.Rule.VisitorModifier.RunAfterRule.Mode.REGARDLESS_WHETHER_RUN_AFTER_RULE_IS_LOADED_OR_DISABLED
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import com.pinterest.ktlint.rule.engine.core.api.SinceKtlint
import com.pinterest.ktlint.rule.engine.core.api.SinceKtlint.Status.EXPERIMENTAL
import com.pinterest.ktlint.rule.engine.core.api.SinceKtlint.Status.STABLE
import com.pinterest.ktlint.rule.engine.core.api.editorconfig.INDENT_SIZE_PROPERTY
import com.pinterest.ktlint.rule.engine.core.api.editorconfig.INDENT_STYLE_PROPERTY
import com.pinterest.ktlint.rule.engine.core.api.firstChildLeafOrSelf20
import com.pinterest.ktlint.rule.engine.core.api.ifAutocorrectAllowed
import com.pinterest.ktlint.rule.engine.core.api.isWhiteSpaceWithNewline20
import com.pinterest.ktlint.rule.engine.core.api.isWhiteSpaceWithoutNewline20
import com.pinterest.ktlint.rule.engine.core.api.lastChildLeafOrSelf20
import com.pinterest.ktlint.rule.engine.core.api.nextLeaf
import com.pinterest.ktlint.rule.engine.core.api.prevLeaf
import com.pinterest.ktlint.rule.engine.core.api.upsertWhitespaceBeforeMe
import com.pinterest.ktlint.ruleset.standard.StandardRule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.PsiCommentImpl
import org.jetbrains.kotlin.psi.psiUtil.leaves

/**
 * A block comment following another element on the same line is replaced with an EOL comment, if possible.
 */
@SinceKtlint("0.49.1", EXPERIMENTAL)
@SinceKtlint("1.0", STABLE)
public class NoSingleLineBlockCommentRule :
    StandardRule(
        id = "no-single-line-block-comment",
        usesEditorConfigProperties =
            setOf(
                INDENT_SIZE_PROPERTY,
                INDENT_STYLE_PROPERTY,
            ),
        visitorModifiers = setOf(RunAfterRule(COMMENT_WRAPPING_RULE_ID, REGARDLESS_WHETHER_RUN_AFTER_RULE_IS_LOADED_OR_DISABLED)),
    ),
    Rule.OfficialCodeStyle {
    override fun beforeVisitChildNodes(
        node: ASTNode,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> AutocorrectDecision,
    ) {
        if (node.elementType == BLOCK_COMMENT) {
            val afterBlockComment =
                node
                    .leaves()
                    .takeWhile { it.isWhiteSpaceWithoutNewline20 }
                    .firstOrNull()
                    ?: node.lastChildLeafOrSelf20

            if (!node.textContains('\n') &&
                afterBlockComment.nextLeaf.isWhitespaceWithNewlineOrNull()
            ) {
                emit(node.startOffset, "Replace the block comment with an EOL comment", true)
                    .ifAutocorrectAllowed {
                        val beforeBlockComment =
                            node
                                .leaves(false)
                                .takeWhile { it.isWhiteSpaceWithoutNewline20 }
                                .firstOrNull()
                                ?: node.firstChildLeafOrSelf20
                        beforeBlockComment
                            .prevLeaf
                            .takeIf { !it.isWhitespaceWithNewlineOrNull() }
                            ?.let {
                                node.upsertWhitespaceBeforeMe(" ")
                            }
                        node.replaceWithEndOfLineComment()
                    }
            }
        }
    }

    private fun ASTNode.replaceWithEndOfLineComment() {
        val content = text.removeSurrounding("/*", "*/").trim()
        val eolComment = PsiCommentImpl(EOL_COMMENT, "// $content")
        (this as LeafPsiElement).rawInsertBeforeMe(eolComment)
        rawRemove()
    }

    private fun ASTNode?.isWhitespaceWithNewlineOrNull() = this == null || this.isWhiteSpaceWithNewline20
}

public val NO_SINGLE_LINE_BLOCK_COMMENT_RULE_ID: RuleId = NoSingleLineBlockCommentRule().ruleId
