/* $*************** KCG Version 6.1.3 (build i6) ****************
** Command: s2c613 -config H:/enseeiht/2IMA/SystemesTempsReel/tp3/Simulation\kcg_s2c_config.txt
** Generation date: 2017-02-15T09:25:00
*************************************************************$ */
#ifndef _counter3_H_
#define _counter3_H_

#include "kcg_types.h"
#include "counter2.h"
#include "counter.h"

/* ========================  input structure  ====================== */
typedef struct {
  kcg_bool /* counter3::reset */ reset;
  kcg_bool /* counter3::c */ c;
} inC_counter3;

/* ========================  context type  ========================= */
typedef struct {
  /* ---------------------------  outputs  --------------------------- */
  kcg_int /* counter3::n */ n;
  /* -----------------------  no local probes  ----------------------- */
  /* -------------------- initialization variables  ------------------ */
  kcg_bool init;
  /* ----------------------- local memories  ------------------------- */
  kcg_int /* counter3::_L14 */ _L14;
  kcg_int /* counter3::_L15 */ _L15;
  /* ---------------------  sub nodes' contexts  --------------------- */
  outC_counter2 /* 2 */ _1_Context_2;
  outC_counter /* 2 */ Context_2;
  /* ------------------ clocks of observable data -------------------- */
  kcg_bool tmp;
  kcg_bool tmp2;
  /* -------------------- (-debug) no assertions  -------------------- */
  /* ------------------- (-debug) local variables -------------------- */
  kcg_bool /* counter3::_L10 */ _L10;
  kcg_bool /* counter3::_L11 */ _L11;
  kcg_bool /* counter3::_L13 */ _L13;
  kcg_bool /* counter3::_L16 */ _L16;
  kcg_bool /* counter3::_L17 */ _L17;
  kcg_bool /* counter3::_L18 */ _L18;
  kcg_int /* counter3::_L19 */ _L19;
  kcg_bool /* counter3::_L20 */ _L20;
} outC_counter3;

/* ===========  node initialization and cycle functions  =========== */
/* counter3 */
extern void counter3(inC_counter3 *inC, outC_counter3 *outC);

extern void counter3_reset(outC_counter3 *outC);

#endif /* _counter3_H_ */
/* $*************** KCG Version 6.1.3 (build i6) ****************
** counter3.h
** Generation date: 2017-02-15T09:25:00
*************************************************************$ */

