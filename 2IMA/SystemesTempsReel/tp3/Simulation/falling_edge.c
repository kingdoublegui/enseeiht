/* $*************** KCG Version 6.1.3 (build i6) ****************
** Command: s2c613 -config H:/enseeiht/2IMA/SystemesTempsReel/tp3/Simulation\kcg_s2c_config.txt
** Generation date: 2017-02-15T08:32:28
*************************************************************$ */

#include "kcg_consts.h"
#include "kcg_sensors.h"
#include "falling_edge.h"

void falling_edge_reset(outC_falling_edge *outC)
{
  outC->init = kcg_true;
}

/* falling_edge */
void falling_edge(inC_falling_edge *inC, outC_falling_edge *outC)
{
  outC->_L6 = outC->_L8;
  outC->_L8 = inC->X;
  outC->_L7 = inC->X;
  outC->_L5 = !outC->_L7;
  outC->_L4 = outC->_L5 & outC->_L6;
  outC->_L2 = inC->X;
  outC->_L3 = !outC->_L2;
  if (outC->init) {
    outC->_L1 = outC->_L3;
  }
  else {
    outC->_L1 = outC->_L4;
  }
  outC->Y = outC->_L1;
  outC->init = kcg_false;
}

/* $*************** KCG Version 6.1.3 (build i6) ****************
** falling_edge.c
** Generation date: 2017-02-15T08:32:28
*************************************************************$ */

