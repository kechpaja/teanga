package rules;

import java.util.*;

public class RuleSet {
	
	// private fields (lists)
	private final List<BinarySyntacticRule> birules_;
	private final List<UnarySyntacticRule> unrules_;
	private final List<AgreementRule> agrules_;
	private final List<SemanticRule> semrules_;

	public RuleSet(List<BinarySyntacticRule> birules, List<UnarySyntacticRule> unrules, List<AgreementRule> agrules, List<SemanticRule> semrules) {
		birules_ = birules;
		unrules_ = unrules;
		agrules_ = agrules;
		semrules_ = semrules;
	}
	
	// GETTERS
	public List<BinarySyntacticRule> getBiRules() {
		return birules_;
	}
	
	public List<UnarySyntacticRule> getUnRules() {
		return unrules_;
	}
	
	public List<AgreementRule> getAgRules() {
		return agrules_;
	}
	
	public List<SemanticRule> getSemRules() {
		return semrules_;
	}
	
}
