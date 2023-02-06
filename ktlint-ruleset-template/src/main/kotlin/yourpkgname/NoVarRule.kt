package yourpkgname

import com.pinterest.ktlint.ruleset.core.api.ElementType
import com.pinterest.ktlint.ruleset.core.api.Rule
import com.pinterest.ktlint.ruleset.core.api.RuleId
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

public class NoVarRule : Rule(RuleId("$CUSTOM_RULE_SET_ID:no-var")) {
    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit,
    ) {
        if (node.elementType == ElementType.VAR_KEYWORD) {
            emit(node.startOffset, "Unexpected var, use val instead", false)
        }
    }
}
