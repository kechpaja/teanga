package rules;

import java.util.*;

public class RuleSet {
	
	// private fields (lists)
	private List<SyntacticRule> synrules_;
	private List<AgreementRule> agrules_;
	private List<SemanticRule> semrules_;

	public RuleSet(List<SyntacticRule> synrules, List<AgreementRule> agrules, List<SemanticRule> semrules) {
		synrules_ = synrules;
		agrules_ = agrules;
		semrules_ = semrules;
	}
	
	// GETTERS
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
