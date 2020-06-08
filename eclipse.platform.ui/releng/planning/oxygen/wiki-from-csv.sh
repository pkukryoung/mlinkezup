#!/bin/bash

create_wiki_header() {
echo "Overview page of the work done for the Eclipse 4.6 service and 4.7 release. For the general themes of this release please see [https://wiki.eclipse.org/Platform_UI/Plan/4.7 Platform UI targets for Eclipse 4.7] "
echo ""
echo "'''This list is generated automatically, please do not update manually'''"
echo ""
echo "This list is your planning backlog for the Eclipse 4.6 release. The list will be refreshed in order to better adjust the priorities of the bugs during the release."
echo ""
}

create_wiki_section () {
F_TARGET="$1" ; shift
F_SEARCH="$1" ; shift

NUM=1
curl -o search.csv 'https://bugs.eclipse.org/bugs/buglist.cgi?classification=Eclipse&component=Runtime&component=IDE&component=User%20Assistance&component=UI&component=Text&order=bug_status&list_id=5935738&product=Platform&query_format=advanced&target_milestone='$F_SEARCH'&query_based_on=&columnlist=bug_id%2Ctarget_milestone%2Cassigned_to%2Cbug_status%2Cresolution%2Cshort_desc%2Cbug_severity%2Ccomponent&ctype=csv'
grep -v target_milestone search.csv >t1 ; mv t1 search.csv


echo "== $F_TARGET =="
echo ""
echo '{| class="wikitable sortable" border="1"'
echo '|-'
echo '! !! Bug !! TM !! Component !! Sev !! Assign !! Status !! Title'

while read line; do
	BUG=$( echo $line | csvtool col 1 - )
	TARGET=$( echo $line | csvtool col 2 - )
	ASSIGNED_TO=$( echo $line | csvtool col 3 - )
	STATUS=$( echo $line | csvtool col 4 - )
	if [ CLOSED = "$STATUS" -o RESOLVED = "$STATUS" -o VERIFIED = "$STATUS" ]; then
		STATUS=$( echo $line | csvtool col 5 - )
		PRE="<strike>"
		POST="</strike>"
	fi
	TITLE=$( echo $line | csvtool col 6 - )
	SEV=$( echo $line | csvtool col 7 - )
	COMPONENT=$( echo $line | csvtool col 8 - )
        if [ "$TITLE -gt 100" ]; then
		TITLE="${TITLE:0:100}"
	fi

	echo '|-'
	echo "| $NUM || $PRE{{bug|$BUG}}$POST || $TARGET || $COMPONENT || $SEV || $ASSIGNED_TO || $STATUS || $PRE$TITLE$POST"
	BUG=""
	TARGET=""
	TITLE=""
	STATUS=""
	ASSIGNED_TO=""
	SEV=""
	PRE=""
	POST=""
	COMPONENT=""
	(( NUM = NUM + 1 ))
done < search.csv

echo '|-'
echo '|}'
echo ""
echo "Last Generated on '''$(date)'''"
echo ""

}

create_wiki_header

echo "= Work targeting Eclipse 4.6.1 and 4.6.2 ="

create_wiki_section "4.6.1" 4.6.1
create_wiki_section "4.6.2" 4.6.2
create_wiki_section "4.6.3" 4.6.3


echo "= Work targeting Eclipse 4.7 ="
echo ""

create_wiki_section "4.7 M1" 4.7%20M1
create_wiki_section "4.7 M2" 4.7%20M2
create_wiki_section "4.7 M3" 4.7%20M3
create_wiki_section "4.7 M4" 4.7%20M4
create_wiki_section "4.7 M5" 4.7%20M5
create_wiki_section "4.7 M6" 4.7%20M6
create_wiki_section "4.7" 4.7




