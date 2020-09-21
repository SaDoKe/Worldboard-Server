package org.sadoke.worldboard.services;

import org.sadoke.worldboard.request.TrainingEntity;
import org.sadoke.worldboard.request.TrainingEntity.Accelerator;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import weka.classifiers.AbstractClassifier;
import weka.core.Capabilities;
import weka.core.Instances;
import weka.core.RevisionUtils;

@Service
@Slf4j
public class WekaWrapper extends AbstractClassifier {

	/**
	 * Returns only the toString() method.
	 *
	 * @return a string describing the classifier
	 */
	public String globalInfo() {
		return toString();
	}

	/**
	 * Returns the capabilities of this classifier.
	 *
	 * @return the capabilities
	 */
	@Override
	public Capabilities getCapabilities() {
		weka.core.Capabilities result = new weka.core.Capabilities(this);

		result.enable(weka.core.Capabilities.Capability.NOMINAL_ATTRIBUTES);
		result.enable(weka.core.Capabilities.Capability.NUMERIC_ATTRIBUTES);
		result.enable(weka.core.Capabilities.Capability.DATE_ATTRIBUTES);
		result.enable(weka.core.Capabilities.Capability.MISSING_VALUES);
		result.enable(weka.core.Capabilities.Capability.NOMINAL_CLASS);
		result.enable(weka.core.Capabilities.Capability.MISSING_CLASS_VALUES);

		result.setMinimumNumberInstances(0);

		return result;
	}

	/**
	 * only checks the data against its capabilities.
	 *
	 * @param i the training data
	 */
	@Override
	public void buildClassifier(Instances i) throws Exception {
		// can classifier handle the data?
		getCapabilities().testWithFail(i);
	}

	public boolean classify(TrainingEntity e) {
		try {

			return classifyInstance(e) < 0.5;
		} catch (Exception e1) {

		}
		return false;

	}

	/**
	 * Classifies the given instance.
	 * 
	 * @param e
	 *
	 * @param i the instance to classify
	 * @return the classification result
	 */
	public double classifyInstance(TrainingEntity e) throws Exception {
		Object[] s = new Object[20];
		s[0] = e.getMovementVector();
		int i = 1;
		for (Accelerator a : e.getAccel()) {
			s[i++]=a.getAccelX();
			s[i++]=a.getAccelY();
			s[i++]=a.getAccelZ();
		}

		// set class value to missing
		s[19] = null;

		return WekaClassifier.classify(s);
	}

	/**
	 * Returns the revision string.
	 * 
	 * @return the revision
	 */
	@Override
	public String getRevision() {
		return RevisionUtils.extract("1.0");
	}

	/**
	 * Returns only the classnames and what classifier it is based on.
	 *
	 * @return a short description
	 */
	@Override
	public String toString() {
		return "Auto-generated classifier wrapper, based on weka.classifiers.trees.J48 (generated with Weka 3.8.4).\n"
				+ this.getClass().getName() + "/WekaClassifier";
	}

	/**
	 * Runs the classfier from commandline.
	 *
	 * @param args the commandline arguments
	 */
	public static void main(String args[]) {
		runClassifier(new WekaWrapper(), args);
	}
}

class WekaClassifier {

	public static double classify(Object[] i) throws Exception {

		double p = Double.NaN;
		p = WekaClassifier.N5d971fe50(i);
		return p;
	}

	static double N5d971fe50(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 1;
		} else if (((Double) i[5]).doubleValue() <= 6.8069233894348145) {
			p = WekaClassifier.N3b78bf971(i);
		} else if (((Double) i[5]).doubleValue() > 6.8069233894348145) {
			p = WekaClassifier.N304a0d7a50(i);
		}
		return p;
	}

	static double N3b78bf971(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.18179455498082042) {
			p = WekaClassifier.N4c7acab92(i);
		} else if (((Double) i[0]).doubleValue() > 0.18179455498082042) {
			p = WekaClassifier.N79b09b2347(i);
		}
		return p;
	}

	static double N4c7acab92(Object[] i) {
		double p = Double.NaN;
		if (i[16] == null) {
			p = 1;
		} else if (((Double) i[16]).doubleValue() <= 2.0878076553344727) {
			p = WekaClassifier.N43ea6983(i);
		} else if (((Double) i[16]).doubleValue() > 2.0878076553344727) {
			p = WekaClassifier.N6333ecfc33(i);
		}
		return p;
	}

	static double N43ea6983(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 4.130124092102051) {
			p = WekaClassifier.N61b3f9cd4(i);
		} else if (((Double) i[2]).doubleValue() > 4.130124092102051) {
			p = WekaClassifier.Ndbc5c7829(i);
		}
		return p;
	}

	static double N61b3f9cd4(Object[] i) {
		double p = Double.NaN;
		if (i[13] == null) {
			p = 1;
		} else if (((Double) i[13]).doubleValue() <= -5.645699977874756) {
			p = 1;
		} else if (((Double) i[13]).doubleValue() > -5.645699977874756) {
			p = WekaClassifier.N1f49666b5(i);
		}
		return p;
	}

	static double N1f49666b5(Object[] i) {
		double p = Double.NaN;
		if (i[18] == null) {
			p = 1;
		} else if (((Double) i[18]).doubleValue() <= 7.4868974685668945) {
			p = WekaClassifier.N5fc58396(i);
		} else if (((Double) i[18]).doubleValue() > 7.4868974685668945) {
			p = WekaClassifier.N25c6d38b19(i);
		}
		return p;
	}

	static double N5fc58396(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.04775484347955544) {
			p = WekaClassifier.N740734787(i);
		} else if (((Double) i[0]).doubleValue() > 0.04775484347955544) {
			p = WekaClassifier.N3609e00417(i);
		}
		return p;
	}

	static double N740734787(Object[] i) {
		double p = Double.NaN;
		if (i[12] == null) {
			p = 1;
		} else if (((Double) i[12]).doubleValue() <= 5.164450645446777) {
			p = WekaClassifier.Na04c6a48(i);
		} else if (((Double) i[12]).doubleValue() > 5.164450645446777) {
			p = 1;
		}
		return p;
	}

	static double Na04c6a48(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 1.3767080307006836) {
			p = WekaClassifier.N5eaa93b19(i);
		} else if (((Double) i[1]).doubleValue() > 1.3767080307006836) {
			p = 1;
		}
		return p;
	}

	static double N5eaa93b19(Object[] i) {
		double p = Double.NaN;
		if (i[8] == null) {
			p = 1;
		} else if (((Double) i[8]).doubleValue() <= -6.895511627197266) {
			p = WekaClassifier.N397035cd10(i);
		} else if (((Double) i[8]).doubleValue() > -6.895511627197266) {
			p = WekaClassifier.N5884abae12(i);
		}
		return p;
	}

	static double N397035cd10(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() <= 1.6616266965866089) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() > 1.6616266965866089) {
			p = WekaClassifier.N7419726f11(i);
		}
		return p;
	}

	static double N7419726f11(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 1;
		} else if (((Double) i[5]).doubleValue() <= -9.191620826721191) {
			p = 1;
		} else if (((Double) i[5]).doubleValue() > -9.191620826721191) {
			p = 0;
		}
		return p;
	}

	static double N5884abae12(Object[] i) {
		double p = Double.NaN;
		if (i[14] == null) {
			p = 1;
		} else if (((Double) i[14]).doubleValue() <= -1.118126392364502) {
			p = WekaClassifier.N52b99c1413(i);
		} else if (((Double) i[14]).doubleValue() > -1.118126392364502) {
			p = WekaClassifier.N556a98fd15(i);
		}
		return p;
	}

	static double N52b99c1413(Object[] i) {
		double p = Double.NaN;
		if (i[17] == null) {
			p = 0;
		} else if (((Double) i[17]).doubleValue() <= -9.56273365020752) {
			p = WekaClassifier.N6a5f05d314(i);
		} else if (((Double) i[17]).doubleValue() > -9.56273365020752) {
			p = 1;
		}
		return p;
	}

	static double N6a5f05d314(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() <= 1.0295381546020508) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() > 1.0295381546020508) {
			p = 1;
		}
		return p;
	}

	static double N556a98fd15(Object[] i) {
		double p = Double.NaN;
		if (i[17] == null) {
			p = 0;
		} else if (((Double) i[17]).doubleValue() <= -0.84517902135849) {
			p = 0;
		} else if (((Double) i[17]).doubleValue() > -0.84517902135849) {
			p = WekaClassifier.N284e902516(i);
		}
		return p;
	}

	static double N284e902516(Object[] i) {
		double p = Double.NaN;
		if (i[13] == null) {
			p = 1;
		} else if (((Double) i[13]).doubleValue() <= -0.05267404764890671) {
			p = 1;
		} else if (((Double) i[13]).doubleValue() > -0.05267404764890671) {
			p = 0;
		}
		return p;
	}

	static double N3609e00417(Object[] i) {
		double p = Double.NaN;
		if (i[12] == null) {
			p = 1;
		} else if (((Double) i[12]).doubleValue() <= -2.0830190181732178) {
			p = WekaClassifier.N246a1a8b18(i);
		} else if (((Double) i[12]).doubleValue() > -2.0830190181732178) {
			p = 1;
		}
		return p;
	}

	static double N246a1a8b18(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() <= -1.97048819065094) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() > -1.97048819065094) {
			p = 1;
		}
		return p;
	}

	static double N25c6d38b19(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() <= -2.0590763092041016) {
			p = WekaClassifier.N79d5b0ae20(i);
		} else if (((Double) i[4]).doubleValue() > -2.0590763092041016) {
			p = WekaClassifier.N25765021(i);
		}
		return p;
	}

	static double N79d5b0ae20(Object[] i) {
		double p = Double.NaN;
		if (i[18] == null) {
			p = 0;
		} else if (((Double) i[18]).doubleValue() <= 9.344854354858398) {
			p = 0;
		} else if (((Double) i[18]).doubleValue() > 9.344854354858398) {
			p = 1;
		}
		return p;
	}

	static double N25765021(Object[] i) {
		double p = Double.NaN;
		if (i[17] == null) {
			p = 0;
		} else if (((Double) i[17]).doubleValue() <= 0.011971374042332172) {
			p = WekaClassifier.N746f9a9d22(i);
		} else if (((Double) i[17]).doubleValue() > 0.011971374042332172) {
			p = WekaClassifier.N67a6451023(i);
		}
		return p;
	}

	static double N746f9a9d22(Object[] i) {
		double p = Double.NaN;
		if (i[18] == null) {
			p = 0;
		} else if (((Double) i[18]).doubleValue() <= 10.218765258789062) {
			p = 0;
		} else if (((Double) i[18]).doubleValue() > 10.218765258789062) {
			p = 1;
		}
		return p;
	}

	static double N67a6451023(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 1;
		} else if (((Double) i[5]).doubleValue() <= 2.971295118331909) {
			p = 1;
		} else if (((Double) i[5]).doubleValue() > 2.971295118331909) {
			p = WekaClassifier.N3322c98a24(i);
		}
		return p;
	}

	static double N3322c98a24(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= -1.2785427570343018) {
			p = WekaClassifier.N181eae4825(i);
		} else if (((Double) i[7]).doubleValue() > -1.2785427570343018) {
			p = WekaClassifier.N45f62d6427(i);
		}
		return p;
	}

	static double N181eae4825(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 9.179649353027344) {
			p = WekaClassifier.N10ad74a426(i);
		} else if (((Double) i[6]).doubleValue() > 9.179649353027344) {
			p = 1;
		}
		return p;
	}

	static double N10ad74a426(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 1;
		} else if (((Double) i[4]).doubleValue() <= -1.4605076313018799) {
			p = 1;
		} else if (((Double) i[4]).doubleValue() > -1.4605076313018799) {
			p = 0;
		}
		return p;
	}

	static double N45f62d6427(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.17238777875900269) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() > 0.17238777875900269) {
			p = WekaClassifier.N60bdd2c928(i);
		}
		return p;
	}

	static double N60bdd2c928(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() <= 9.361614227294922) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() > 9.361614227294922) {
			p = 0;
		}
		return p;
	}

	static double Ndbc5c7829(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.058623807942219136) {
			p = WekaClassifier.N764f013f30(i);
		} else if (((Double) i[0]).doubleValue() > 0.058623807942219136) {
			p = WekaClassifier.Nc833e8832(i);
		}
		return p;
	}

	static double N764f013f30(Object[] i) {
		double p = Double.NaN;
		if (i[11] == null) {
			p = 1;
		} else if (((Double) i[11]).doubleValue() <= 6.035966873168945) {
			p = 1;
		} else if (((Double) i[11]).doubleValue() > 6.035966873168945) {
			p = WekaClassifier.N2268f9f31(i);
		}
		return p;
	}

	static double N2268f9f31(Object[] i) {
		double p = Double.NaN;
		if (i[17] == null) {
			p = 1;
		} else if (((Double) i[17]).doubleValue() <= 6.217931747436523) {
			p = 1;
		} else if (((Double) i[17]).doubleValue() > 6.217931747436523) {
			p = 0;
		}
		return p;
	}

	static double Nc833e8832(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() <= -0.39266106486320496) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() > -0.39266106486320496) {
			p = 1;
		}
		return p;
	}

	static double N6333ecfc33(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.024234349670906867) {
			p = WekaClassifier.N6dfb5f5c34(i);
		} else if (((Double) i[0]).doubleValue() > 0.024234349670906867) {
			p = 1;
		}
		return p;
	}

	static double N6dfb5f5c34(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= -4.656864643096924) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() > -4.656864643096924) {
			p = WekaClassifier.N5b976f0b35(i);
		}
		return p;
	}

	static double N5b976f0b35(Object[] i) {
		double p = Double.NaN;
		if (i[8] == null) {
			p = 1;
		} else if (((Double) i[8]).doubleValue() <= -8.482915878295898) {
			p = 1;
		} else if (((Double) i[8]).doubleValue() > -8.482915878295898) {
			p = WekaClassifier.N4e757d8f36(i);
		}
		return p;
	}

	static double N4e757d8f36(Object[] i) {
		double p = Double.NaN;
		if (i[11] == null) {
			p = 1;
		} else if (((Double) i[11]).doubleValue() <= -7.00085973739624) {
			p = WekaClassifier.N32dd2cb137(i);
		} else if (((Double) i[11]).doubleValue() > -7.00085973739624) {
			p = WekaClassifier.N395096c44(i);
		}
		return p;
	}

	static double N32dd2cb137(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 1;
		} else if (((Double) i[5]).doubleValue() <= -4.0439300537109375) {
			p = WekaClassifier.N5eb840938(i);
		} else if (((Double) i[5]).doubleValue() > -4.0439300537109375) {
			p = 0;
		}
		return p;
	}

	static double N5eb840938(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() <= -2.3655436038970947) {
			p = WekaClassifier.N65644c8839(i);
		} else if (((Double) i[3]).doubleValue() > -2.3655436038970947) {
			p = WekaClassifier.N18ccae40(i);
		}
		return p;
	}

	static double N65644c8839(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= -0.04309694468975067) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() > -0.04309694468975067) {
			p = 1;
		}
		return p;
	}

	static double N18ccae40(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 1.101366400718689) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() > 1.101366400718689) {
			p = WekaClassifier.N5bfbd0b841(i);
		}
		return p;
	}

	static double N5bfbd0b841(Object[] i) {
		double p = Double.NaN;
		if (i[15] == null) {
			p = 0;
		} else if (((Double) i[15]).doubleValue() <= -1.242628574371338) {
			p = 0;
		} else if (((Double) i[15]).doubleValue() > -1.242628574371338) {
			p = WekaClassifier.N2e9dc0242(i);
		}
		return p;
	}

	static double N2e9dc0242(Object[] i) {
		double p = Double.NaN;
		if (i[12] == null) {
			p = 1;
		} else if (((Double) i[12]).doubleValue() <= 1.5347301959991455) {
			p = 1;
		} else if (((Double) i[12]).doubleValue() > 1.5347301959991455) {
			p = WekaClassifier.N452ec0db43(i);
		}
		return p;
	}

	static double N452ec0db43(Object[] i) {
		double p = Double.NaN;
		if (i[12] == null) {
			p = 0;
		} else if (((Double) i[12]).doubleValue() <= 2.0016138553619385) {
			p = 0;
		} else if (((Double) i[12]).doubleValue() > 2.0016138553619385) {
			p = 1;
		}
		return p;
	}

	static double N395096c44(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 1;
		} else if (((Double) i[4]).doubleValue() <= 1.242628574371338) {
			p = WekaClassifier.N66e77b645(i);
		} else if (((Double) i[4]).doubleValue() > 1.242628574371338) {
			p = 1;
		}
		return p;
	}

	static double N66e77b645(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() <= 0.9648927450180054) {
			p = WekaClassifier.N4aabb2b546(i);
		} else if (((Double) i[5]).doubleValue() > 0.9648927450180054) {
			p = 1;
		}
		return p;
	}

	static double N4aabb2b546(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= -2.858764171600342) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() > -2.858764171600342) {
			p = 0;
		}
		return p;
	}

	static double N79b09b2347(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 3.2418479919433594) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 3.2418479919433594) {
			p = WekaClassifier.N5245331048(i);
		}
		return p;
	}

	static double N5245331048(Object[] i) {
		double p = Double.NaN;
		if (i[12] == null) {
			p = 0;
		} else if (((Double) i[12]).doubleValue() <= 5.583448886871338) {
			p = WekaClassifier.N27e33d2949(i);
		} else if (((Double) i[12]).doubleValue() > 5.583448886871338) {
			p = 0;
		}
		return p;
	}

	static double N27e33d2949(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 1.764580488204956) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() > 1.764580488204956) {
			p = 0;
		}
		return p;
	}

	static double N304a0d7a50(Object[] i) {
		double p = Double.NaN;
		if (i[14] == null) {
			p = 0;
		} else if (((Double) i[14]).doubleValue() <= 7.556331157684326) {
			p = WekaClassifier.N4d5ee1d351(i);
		} else if (((Double) i[14]).doubleValue() > 7.556331157684326) {
			p = 0;
		}
		return p;
	}

	static double N4d5ee1d351(Object[] i) {
		double p = Double.NaN;
		if (i[17] == null) {
			p = 1;
		} else if (((Double) i[17]).doubleValue() <= 5.755836486816406) {
			p = 1;
		} else if (((Double) i[17]).doubleValue() > 5.755836486816406) {
			p = 0;
		}
		return p;
	}
}
