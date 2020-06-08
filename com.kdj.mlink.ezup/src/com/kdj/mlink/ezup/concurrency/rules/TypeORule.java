package com.kdj.mlink.ezup.concurrency.rules;

/*
 * "The Java Developer's Guide to Eclipse"
 *   by D'Anjou, Fairbrother, Kehn, Kellerman, McCarthy
 * 
 * (C) Copyright International Business Machines Corporation, 2003, 2004. 
 * All Rights Reserved.
 * 
 * Code or samples provided herein are provided without warranty of any kind.
 */

import org.eclipse.core.runtime.jobs.ISchedulingRule;

import com.kdj.mlink.ezup.concurrency.TraceUtility;

/**
 * This rule contains itself and conflicts with all other 
 * blood bank rules (universal donor, not receiver).
 */
public class TypeORule implements ISchedulingRule {

   /**
    *  
    */
   public TypeORule() {
      super();
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.eclipse.core.runtime.jobs.ISchedulingRule#contains(org.eclipse.core.runtime.jobs.ISchedulingRule)
    */
   public boolean contains(ISchedulingRule rule) {
      TraceUtility.traceMsg(TraceUtility.RULE, "in " + this + "contains", " sent " + rule);
      if (this == rule)
         return true;
      else
         return false;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.eclipse.core.runtime.jobs.ISchedulingRule#isConflicting(org.eclipse.core.runtime.jobs.ISchedulingRule)
    */
   public boolean isConflicting(ISchedulingRule rule) {
      // JDG2E: 4C_ Type O rule conflicts with everything but itself.
      TraceUtility.traceMsg(TraceUtility.RULE, "in " + this + "isConflicting", " sent " + rule);
      // FIXED must also conflict with itself      
      if (rule instanceof TypeABRule || rule instanceof TypeRule || rule == this)
         return true;
      else
         return false;
   }

}