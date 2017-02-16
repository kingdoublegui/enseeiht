/* $*************** KCG Version 6.1.3 (build i6) ****************
** Command: s2c613 -config H:/enseeiht/2IMA/SystemesTempsReel/tp3/Simulation\kcg_s2c_config.txt
** Generation date: 2017-02-15T08:32:28
*************************************************************$ */
#ifndef _falling_edge_H_
#define _falling_edge_H_

#include "kcg_types.h"

/* ========================  input structure  ====================== */
typedef struct { kcg_bool /* falling_edge::X */ X; } inC_falling_edge;

/* ========================  context type  ========================= */
typedef struct {
  /* ---------------------------  outputs  --------------------------- */
  kcg_bool /* falling_edge::Y */ Y;
  /* -----------------------  no local probes  ----------------------- */
  /* -------------------- initialization variables  ------------------ */
  kcg_bool init;
  /* ----------------------- local memories  ------------------------- */
  kcg_bool /* falling_edge::_L8 */ _L8;
  /* -------------------- no sub nodes' contexts  -------------------- */
  /* ----------------- no clocks of observable data ------------------ */
  /* -------------------- (-debug) no assertions  -------------------- */
  /* ------------------- (-debug) local variables -------------------- */
  kcg_bool /* falling_edge::_L1 */ _L1;
  kcg_bool /* falling_edge::_L2 */ _L2;
  kcg_bool /* falling_edge::_L3 */ _L3;
  kcg_bool /* falling_edge::_L4 */ _L4;
  kcg_bool /* falling_edge::_L5 */ _L5;
  kcg_bool /* falling_edge::_L6 */ _L6;
  kcg_bool /* falling_edge::_L7 */ _L7;
} outC_falling_edge;

/* ===========  node initialization and cycle functions  =========== */
/* falling_edge */
extern void falling_edge(inC_falling_edge *inC, outC_falling_edge *outC);

extern void falling_edge_reset(outC_falling_edge *outC);

#endif /* _falling_edge_H_ */
/* $*************** KCG Version 6.1.3 (build i6) ****************
** falling_edge.h
** Generation date: 2017-02-15T08:32:28
*************************************************************$ */

