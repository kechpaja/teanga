package rules;

import java.util.*;
import parsing.*;

public class RuleSet {
	
	// private fields (lists)
	private final HashMap<Pos, List<BinarySyntacticRule>> birules_;
	private final HashMap<Pos, UnarySyntacticRule> unrules_;
	private final List<TernarySyntacticRule> terules_;

	public RuleSet(HashMap<Pos, List<BinarySyntacticRule>> birules, HashMap<Pos, UnarySyntacticRule> unrules,
			List<TernarySyntacticRule> terules) {
		birules_ = birules;
		unrules_ = unrules;
		terules_ = terules;
	}
	
	// GETTERS
	public HashMap<Pos, List<BinarySyntacticRule>> getBiRules() {
		return birules_;
	}
	
	public HashMap<Pos, UnarySyntacticRule> getUnRules() {
		return unrules_;
	}
	
	public List<TernarySyntacticRule> getTeRules() {
		return terules_;
	}
	
}
