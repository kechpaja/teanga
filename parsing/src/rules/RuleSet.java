package rules;

import java.util.List;

public class RuleSet {
	
	final private List<SyntacticRule> synrules_;
	final private List<AgreementRule> agrules_;
	final private List<SemanticRule> semrules_;
	
	public RuleSet(List<SyntacticRule> synrules, List<AgreementRule> agrules, List<SemanticRule> semrules) {
		synrules_ = synrules;
		agrules_ = agrules;
		semrules_ = semrules;
	}
	
	public List<SyntacticRule> getSynRules() {
		return synrules_;
	}
	
	public List<AgreementRule> getAgRules() {
		return agrules_;
	}
	
	public List<SemanticRule> getSemRules() {
		return semrules_;
	}
}
