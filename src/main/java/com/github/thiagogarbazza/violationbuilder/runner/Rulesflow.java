package com.github.thiagogarbazza.violationbuilder.runner;

/**
 * The flow control of execution of rules.
 *
 * @since 1.1.0
 */
public enum Rulesflow {

  /**
   * When a rule is violated, the flow of execution of rules must continue.
   * <p>
   * <b>Example:</b><br/>
   * ValidationRule rule02 = (violationBuilder, somebOject) -&gt; { ...; return CONTINUE;};<br/>
   * RulesExecutor.rulesExecutor(asList(rule01, rule02, rule03), somebOject);<br/>
   * When executing all rules, even if rule02 has a violation, <b>rule03 will be executed</b>.
   */
  CONTINUE,

  /**
   * When a rule is violated, the flow of execution of rules must stop.
   * <p>
   * <b>Example:</b><br/>
   * ValidationRule rule02 = (violationBuilder, somebOject) -&gt; { ...; return STOP;};<br/>
   * RulesExecutor.rulesExecutor(asList(rule01, rule02, rule03), somebOject);<br/>
   * When executing all rules, even if rule02 has a violation, <b>rule03 will not be executed</b>.
   */
  STOP
}
