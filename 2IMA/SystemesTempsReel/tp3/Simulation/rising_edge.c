/* $*************** KCG Version 6.1.3 (build i6) ****************
** Command: s2c613 -config H:/enseeiht/2IMA/SystemesTempsReel/tp3/Simulation\kcg_s2c_config.txt
** Generation date: 2017-02-15T08:24:34
*************************************************************$ */

#include "kcg_consts.h"
#include "kcg_sensors.h"
#include "rising_edge.h"

void rising_edge_reset(outC_rising_edge *outC)
{
  outC->init = kcg_true;
}

/* rising_edge */
void rising_edge(inC_rising_edge *inC, outC_rising_edge *outC)
{
  outC->_L6 = outC->_L7;
  outC->_L8 = !outC->_L6;
  outC->_L7 = inC->X;
  outC->_L4 = inC->X;
  outC->_L5 = outC->_L4 & outC->_L8;
  outC->_L3 = inC->X;
  if (outC->init) {
    outC->_L2 = outC->_L3;
  }
  else {
    outC->_L2 = outC->_L5;
  }
  outC->Y = outC->_L2;
  outC->init = kcg_false;
}

/* $*************** KCG Version 6.1.3 (build i6) ****************
** rising_edge.c
** Generation date: 2017-02-15T08:24:34
*************************************************************$ */

