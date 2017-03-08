---- MODULE MC ----
EXTENDS jeton_boolean, TLC

\* CONSTANT definitions @modelParameterConstants:0N
const_1488970832766255000 == 
5
----

\* SPECIFICATION definition @modelBehaviorSpec:0
spec_1488970832777256000 ==
Spec
----
\* PROPERTY definition @modelCorrectnessProperties:0
prop_1488970832787257000 ==
TypeInvariant
----
\* PROPERTY definition @modelCorrectnessProperties:1
prop_1488970832797258000 ==
ExclMutuelle
----
\* PROPERTY definition @modelCorrectnessProperties:2
prop_1488970832807259000 ==
VivaciteIndividuelle
----
\* PROPERTY definition @modelCorrectnessProperties:3
prop_1488970832818260000 ==
VivaciteGlobale
----
\* PROPERTY definition @modelCorrectnessProperties:4
prop_1488970832828261000 ==
JetonVaPartout
----
\* PROPERTY definition @modelCorrectnessProperties:5
prop_1488970832838262000 ==
JetonUnique
----
\* PROPERTY definition @modelCorrectnessProperties:6
prop_1488970832848263000 ==
Sanity
----
=============================================================================
\* Modification History
\* Created Wed Mar 08 12:00:32 CET 2017 by tmeunier
